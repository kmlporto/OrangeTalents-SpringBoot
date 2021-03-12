INSERT INTO USUARIO(nome, email, senha) VALUES ('Aluno', 'aluno@email.com', '$2a$10$upnUO2AyXsSSElnOsKVszuLegkRm0xzE72UEmyy48oh21NvsRptTW');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2021-03-11', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida2', 'Projeto não compila', '2021-03-11', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, autor_id, curso_id) VALUES('Dúvida3', 'Tag HTML', '2021-03-11', 1, 2);