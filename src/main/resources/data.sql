INSERT INTO USUARIO(nome, email, senha) VALUES ('Aluno', 'aluno@email.com', '$2a$10$upnUO2AyXsSSElnOsKVszuLegkRm0xzE72UEmyy48oh21NvsRptTW');
INSERT INTO USUARIO(nome, email, senha) VALUES ('Moderador', 'moderador@email.com', '$2a$10$upnUO2AyXsSSElnOsKVszuLegkRm0xzE72UEmyy48oh21NvsRptTW');

INSERT INTO PERFIL(id, nome) VALUES (1, 'ROLE_ALUNO');
INSERT INTO PERFIL(id, nome) VALUES (2, 'ROLE_MODERADOR');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2, 2);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2021-03-11', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida2', 'Projeto não compila', '2021-03-11', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida3', 'Tag HTML', '2021-03-11', 1, 2);