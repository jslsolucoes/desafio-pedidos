INSERT INTO produto (gtin, nome) VALUES ('7894900011517', 'REFRIGERANTE COCA-COLA 2LT');
INSERT INTO produto (gtin, nome) VALUES ('7891910000197', 'AÇÚCAR REFINADO UNIÃO 1KG');
INSERT INTO produto (gtin, nome) VALUES ('7892840222949', 'SALGADINHO FANDANGOS QUEIJO');
INSERT INTO produto (gtin, nome) VALUES ('7891910007110', 'AÇÚCAR DE CONFEITEIRO UNIÃO GLAÇÚCAR');
INSERT INTO produto (gtin, nome) VALUES ('7891000053508', 'ACHOCOLATADO NESCAU 2.0');
INSERT INTO produto (gtin, nome) VALUES ('7891000100103', 'LEITE CONDENSADO MOÇA');
INSERT INTO produto (gtin, nome) VALUES ('7891991010856', 'CERVEJA BUDWEISER');


insert into fornecedor (id,razao_social,cnpj) values (fornecedor_sq.nextval,'Fornecedor 1','00.000.000/00000-00');
insert into pedido (id,id_fornecedor) values (pedido_sq.nextval,fornecedor_sq.currval);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7894900011517',10,1.5);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7891910000197',5,7.56);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7891000053508',3,0.1);


insert into pedido (id,id_fornecedor) values (pedido_sq.nextval,fornecedor_sq.currval);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7891000100103',1,1.5);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7891991010856',2,7.56);
insert into pedido_item (id,id_pedido,id_produto,quantidade,valor) values (pedido_item_sq.nextval,pedido_sq.currval,'7891910007110',3,0.1);