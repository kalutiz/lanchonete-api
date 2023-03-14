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

