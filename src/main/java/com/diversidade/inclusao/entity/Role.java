package com.diversidade.inclusao.entity;

/**
 * Enumeração que define os tipos de perfis (roles) disponíveis para usuários
 * autenticados no sistema. Ter perfis distintos permite controlar o acesso
 * aos recursos de acordo com as responsabilidades de cada grupo. Por exemplo,
 * ADMIN pode gerenciar departamentos, vagas, candidatos e colaboradores, HR
 * tem permissões relacionadas a gestão de pessoas e USER possui acesso
 * somente para operações de leitura.
 */
public enum Role {
    ADMIN,
    HR,
    USER
}