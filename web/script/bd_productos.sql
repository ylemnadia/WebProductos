
drop database bd_productos;
CREATE DATABASE bd_productos;
USE  bd_productos;

CREATE TABLE usuario (
  i_num_id_user INT NOT NULL AUTO_INCREMENT,
  c_user VARCHAR(20) DEFAULT NULL,
  c_password VARCHAR(50) DEFAULT NULL,
  c_perfil VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (i_num_id_user)
);

CREATE TABLE empleado (
  i_num_id_emp INT NOT NULL AUTO_INCREMENT,
  c_dni VARCHAR(8) DEFAULT NULL,
  c_nombres VARCHAR(100) DEFAULT NULL,
  c_correo VARCHAR(100) DEFAULT NULL,
  i_num_id_user INT,
  PRIMARY KEY (i_num_id_emp),
  FOREIGN KEY (i_num_id_user) REFERENCES usuario (i_num_id_user)
);

CREATE TABLE categoria (
  i_num_id_cat INT NOT NULL AUTO_INCREMENT,
  c_descripcion VARCHAR(50) DEFAULT NULL,
  c_estado CHAR(1) DEFAULT NULL,
  PRIMARY KEY (i_num_id_cat)
);

CREATE TABLE producto (
  i_num_id_prod INT NOT NULL AUTO_INCREMENT,
  c_nombre VARCHAR(50) DEFAULT NULL,
  c_descripcion VARCHAR(100) DEFAULT NULL,
  n_precio DOUBLE DEFAULT NULL,
  c_estado CHAR(1) DEFAULT NULL,
  i_num_id_cat INT,
  PRIMARY KEY (i_num_id_prod),
  FOREIGN KEY (i_num_id_cat) REFERENCES categoria (i_num_id_cat)
);

CREATE TABLE proveedor (
  i_num_id_pro INT NOT NULL AUTO_INCREMENT,
  c_dni VARCHAR(8) DEFAULT NULL,
  c_nombres VARCHAR(100) DEFAULT NULL,
  c_correo VARCHAR(100) DEFAULT NULL,
  c_pago DOUBLE DEFAULT NULL,
  PRIMARY KEY (i_num_id_pro)
);


INSERT INTO usuario VALUES(1,'ADMIN','12345678','ADMINISTRADOR');
INSERT INTO usuario VALUES(2,'MARIA','123','USER');

INSERT INTO empleado VALUES(1,'12345678','SISTEMAS','admin@gmail.com',1);
INSERT INTO empleado VALUES(2,'89765432','MARIA DEL CARMEN','maria@gmail.com',2);

INSERT INTO categoria VALUES(1,'CATEGORIA 01','1');
INSERT INTO categoria VALUES(2,'CATEGORIA 02','1');

INSERT INTO producto VALUES(1,'LAPTOP LENOVO 456','LAPTOP LENOVO 456 - 14P - MEMORIA 12',2000.00,'1',1);
INSERT INTO producto VALUES(2,'CELULAR SAMSUNG','CELULAR SAMSUNG 5P-CAMARA 45MP',1000.00,'1',2);

INSERT INTO proveedor VALUES(1,'09931267','JACK CESAR','ja_ce1@gmail.com',549.50);
INSERT INTO proveedor VALUES(2,'72049712','MARTHA PAMELA','mar_pa3@outlook.es',651.90);