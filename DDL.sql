create schema fila;

use fila;

drop user user@localhost;
flush privileges;

create user user@localhost identified by 'pass123';

grant select, insert, delete, update on fila.* to user@localhost;

CREATE TABLE grupo (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    aluno bigint unsigned,
    primary key (id)
);

CREATE TABLE fila (
    id bigint unsigned not null auto_increment,
    professor bigint unsigned,
    hora_inicio TIMESTAMP,
    primary key (id)
);

CREATE TABLE apresentacao (
    id bigint unsigned not null auto_increment,
    grupo bigint unsigned,
    fila bigint unsigned,
    nome varchar(45),
    primary key (id)
);

CREATE TABLE aluno (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id)
);

CREATE TABLE usuario (
    id bigint unsigned not null auto_increment,
    email varchar(45) not null,
    senha varchar(16) not null,
    primary key (id)
);

CREATE TABLE professor (
    id bigint unsigned not null auto_increment,
    nome varchar(45),
    usuario bigint unsigned,
    primary key (id)
);
 
ALTER TABLE grupo ADD CONSTRAINT FK_grupo_3
    FOREIGN KEY (aluno)
    REFERENCES aluno (id);
 
ALTER TABLE fila ADD CONSTRAINT FK_fila_2
    FOREIGN KEY (professor)
    REFERENCES professor (id);
 
ALTER TABLE apresentacao ADD CONSTRAINT FK_apresentacao_2
    FOREIGN KEY (fila)
    REFERENCES fila (id)
    ON DELETE RESTRICT;
 
ALTER TABLE apresentacao ADD CONSTRAINT FK_apresentacao_3
    FOREIGN KEY (grupo)
    REFERENCES grupo (id);
 
ALTER TABLE aluno ADD CONSTRAINT FK_aluno_2
    FOREIGN KEY (usuario)
    REFERENCES usuario (id)
    ON DELETE CASCADE;
 
ALTER TABLE professor ADD CONSTRAINT FK_professor_2
    FOREIGN KEY (usuario)
    REFERENCES usuario (id)
	ON DELETE CASCADE;