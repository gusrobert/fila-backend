create schema fila;

use fila;

create user user@localhost identified by 'pass123';

grant select, insert, delete, update on fila.* to user@localhost;

CREATE TABLE perfil (
    id bigint unsigned not null auto_increment,
    nome varchar(255),
    primary key (id)
);

CREATE TABLE credencial (
    id bigint unsigned not null auto_increment,
    login varchar(255) UNIQUE,
    senha varchar(255),
    sn_bloqueado varchar(255),
    sn_excluido varchar(255),
    sn_online varchar(255),
    perfil bigint unsigned NOT NULL,
	primary key (id),
	FOREIGN KEY (perfil) REFERENCES perfil(id)
);

CREATE TABLE credencial_perfil (
    id_credencial bigint unsigned not null auto_increment,
    id_perfil bigint unsigned NOT NULL,
    primary key (id_credencial, id_perfil),
	foreign key (id_credencial) references credencial(id) on delete restrict on update cascade,
	foreign key (id_perfil) references perfil(id) on delete restrict on update cascade
);
 

CREATE TABLE usuario (
    id bigint unsigned not null auto_increment,
    email varchar(255) not null unique,
    senha varchar(255) not null,
	credencial bigint unsigned,
    primary key (id),
	foreign key (credencial) references credencial(id)
);

CREATE TABLE aluno (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id),
	foreign key (usuario) references usuario(id)
);

CREATE TABLE grupo (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    aluno bigint unsigned,
    primary key (id),
	foreign key (aluno) references aluno(id)
);

CREATE TABLE professor (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id),
	foreign key (usuario) references usuario(id)
);

CREATE TABLE fila (
    id bigint unsigned not null auto_increment,
    professor bigint unsigned,
    hora_inicio TIMESTAMP,
    primary key (id),
	foreign key (professor) references professor(id)
);

CREATE TABLE apresentacao (
    id bigint unsigned not null auto_increment,
    grupo bigint unsigned,
    fila bigint unsigned,
    nome varchar(45),
    primary key (id),
	foreign key (fila) references fila(id),
	foreign key (grupo) references grupo(id)
);