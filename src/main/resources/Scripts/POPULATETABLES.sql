insert into credencial (login, senha, sn_bloqueado, sn_excluido, sn_online) values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', false, false, false);

insert into usuario (email, credencial) values ("gustavo@email.com", 1);

insert into perfil (nome) values ('ROLE_ADMIN');

insert into credencial_perfil values (1, 1);
