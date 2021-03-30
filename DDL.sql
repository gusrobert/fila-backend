create schema fila;

use fila;

create user user@localhost identified by 'pass123';

grant select, insert, delete, update on fila.* to user@localhost;

create table perfil (
    id bigint unsigned not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table credencial (
    id bigint unsigned not null auto_increment,
    login varchar(255) unique,
    senha varchar(255),
    sn_bloqueado boolean,
    sn_excluido boolean,
    sn_online boolean
	primary key (id)
);

create table credencial_perfil (
    id_credencial bigint unsigned not null,
    id_perfil bigint unsigned not null,
    primary key (id_credencial, id_perfil),
	foreign key (id_credencial) references credencial(id) on delete restrict on update cascade,
	foreign key (id_perfil) references perfil(id) on delete restrict on update cascade
);
 

create table usuario (
    id bigint unsigned not null auto_increment,
    email varchar(255) not null unique
	credencial bigint unsigned,
    primary key (id),
	foreign key (credencial) references credencial(id)
);

create table aluno (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id),
	foreign key (usuario) references usuario(id)
);

create table grupo (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    aluno bigint unsigned,
    primary key (id),
	foreign key (aluno) references aluno(id)
);

create table grupo_aluno (
    id_grupo bigint unsigned not null,
    id_aluno bigint unsigned NOT NULL,
    primary key (id_grupo, id_aluno),
	foreign key (id_grupo) references grupo(id) on delete restrict on update cascade,
	foreign key (id_aluno) references aluno(id) on delete restrict on update cascade
);

create table professor (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id),
	foreign key (usuario) references usuario(id)
);

create table fila (
    id bigint unsigned not null auto_increment,
    professor bigint unsigned,
    hora_inicio TIMESTAMP,
    primary key (id),
	foreign key (professor) references professor(id)
);

create table apresentacao (
    id bigint unsigned not null auto_increment,
    grupo bigint unsigned,
    fila bigint unsigned,
    nome varchar(45),
    primary key (id),
	foreign key (fila) references fila(id),
	foreign key (grupo) references grupo(id)
);