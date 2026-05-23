package com.diversidade.inclusao.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

/**
 * Serviço utilitário responsável por gerar, validar e extrair informações
 * dos tokens JWT usados na autenticação. A chave secreta utilizada para
 * assinar os tokens é configurada em application.properties através da
 * propriedade security.jwt.secret. Tokens são gerados com uma validade
 * padrão de 24 horas.
 */
@Service
public class JwtService {

    /** Chave secreta usada para assinar e verificar JWTs. */
    @Value("${security.jwt.secret}")
    private String jwtSecret;

    /**
     * Extrai o nome de usuário (subject) do token JWT.
     *
     * @param token JWT
     * @return subject (nome de usuário)
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai um claim do token usando uma função fornecida.
     *
     * @param token JWT
     * @param claimsResolver função para extrair o claim desejado
     * @param <T> tipo do claim
     * @return valor do claim extraído
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Gera um token JWT para o usuário informado. O token contém o nome de
     * usuário como subject e o perfil (role) como claim adicional. A validade
     * padrão é de 24 horas a partir do momento de geração.
     *
     * @param userDetails usuário autenticado
     * @return token JWT assinado
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().stream()
                        .findFirst()
                        .map(Object::toString)
                        .orElse(""))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Verifica se o token é válido comparando o nome de usuário e a data de
     * expiração.
     *
     * @param token JWT
     * @param userDetails detalhes do usuário autenticado
     * @return verdadeiro se o token é válido
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Verifica se o token expirou.
     *
     * @param token JWT
     * @return verdadeiro se o token está expirado
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai a data de expiração do token.
     *
     * @param token JWT
     * @return data de expiração
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrai todos os claims do token usando a chave secreta.
     *
     * @param token JWT
     * @return claims contidos no token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Converte a chave secreta configurada em uma instância {@link Key}. A
     * biblioteca JJWT recomenda o uso de chaves com no mínimo 256 bits para
     * o algoritmo HS256.
     *
     * @return chave criptográfica derivada da string configurada
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}