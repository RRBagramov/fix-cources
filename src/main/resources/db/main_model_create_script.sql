CREATE TABLE kfu_user
(
  id INTEGER DEFAULT nextval('user_id_seq'::regclass) PRIMARY KEY NOT NULL,
  login VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON kfu_user (id);
CREATE UNIQUE INDEX user_login_uindex ON kfu_user (login);
CREATE TABLE product
(
  id INTEGER DEFAULT nextval('products_id_seq'::regclass) PRIMARY KEY NOT NULL,
  name VARCHAR(50),
  type VARCHAR(50),
  price DOUBLE PRECISION
);
CREATE UNIQUE INDEX products_id_uindex ON product (id);