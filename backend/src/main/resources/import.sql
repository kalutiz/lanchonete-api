INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_ingrediente (nome, preco) VALUES ('ALFACE', '0.4');
INSERT INTO tb_ingrediente (nome, preco) VALUES ('BACON', '2');
INSERT INTO tb_ingrediente (nome, preco) VALUES ('HAMBURGUER', '3');
INSERT INTO tb_ingrediente (nome, preco) VALUES ('OVO', '0.8');
INSERT INTO tb_ingrediente (nome, preco) VALUES ('QUEIJO', '1.5');

INSERT INTO tb_lanche (nome) VALUES ('X-BACON');
INSERT INTO tb_lanche (nome) VALUES ('X-BURGUER');
INSERT INTO tb_lanche (nome) VALUES ('X-EGG');
INSERT INTO tb_lanche (nome) VALUES ('X-EGG BACON');

INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (1,2);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (1,3);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (1,5);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (2,3);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (2,5);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (3,3);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (3,4);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (3,5);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (4,2);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (4,3);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (4,4);
INSERT INTO tb_lanche_ingrediente (lanche_id, ingrediente_id) VALUES (4,5);

