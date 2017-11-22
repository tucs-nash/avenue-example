INSERT INTO product (id, name, description, parent_product_id) VALUES (1, 'product 1', 'product 1 desc', null);
INSERT INTO product (id, name, description, parent_product_id) VALUES (2, 'product 2', 'product 2 desc', null);
INSERT INTO product (id, name, description, parent_product_id) VALUES (3, 'product 3', 'product 3 desc', 1);
INSERT INTO product (id, name, description, parent_product_id) VALUES (4, 'product 4', 'product 4 desc', 2);

INSERT INTO image (id, type, product_id) VALUES (1, 'image 1', 1);
INSERT INTO image (id, type, product_id) VALUES (2, 'image 2', 1);
INSERT INTO image (id, type, product_id) VALUES (3, 'image 3', 2);
INSERT INTO image (id, type, product_id) VALUES (4, 'image 4', 3);
INSERT INTO image (id, type, product_id) VALUES (5, 'image 5', 3);
INSERT INTO image (id, type, product_id) VALUES (6, 'image 6', 4);