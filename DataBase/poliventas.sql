
create database poliventas;
use poliventas;

create table cuenta(
usuario 	varchar(15) primary key,
contraseña 	varchar(15),
rol			varchar(15),
eliminado	boolean default false);

create table persona(
cedula		varchar(10) primary key,
nombres		varchar(30),
apellidos	varchar(30),
numero		varchar(10),
correo		varchar(25),
direccion	varchar(50),
matricula 	varchar(10),
saldo		double,
eliminado	boolean default false,
usuario		varchar(15),
foreign key (usuario) references cuenta(usuario));

create table producto(
idproducto			int auto_increment primary key,
nombre				varchar(15),
tiempoMaxEntrega	int,
categoria			varchar(100),
costo				double,
cantidadDisponible	int,
eliminado			boolean default false,
calificacion		int,
vendedor			varchar(10),
foreign key	(vendedor) references persona(cedula));

create table tipodepago(
id			int auto_increment primary key,
descripcion	varchar(20));

create table pedido(
comprador		varchar(10),
producto		int,
cantidad 		int,
estado			varchar(15),
tipopago		int,
fechaPedido		date,
entregadoAtiempo boolean,
primary key(comprador,producto),
foreign key	(comprador) references persona(cedula),
foreign key	(producto) references producto(idproducto),
foreign key	(tipopago) references tipodepago(id));

create table calificacionproducto(
comprador		varchar(10),
producto		int,
calificacion 	int,
primary key(comprador,producto),
foreign key	(comprador) references persona(cedula),
foreign key	(producto) references producto(idproducto));

create table calificacionvendedor(
comprador		varchar(10),
vendedor		varchar(10),
calificacion 	int,
primary key(comprador,vendedor),
foreign key	(comprador) references persona(cedula),
foreign key	(vendedor) references persona(cedula));

create table categoria(
idcategoria		int auto_increment primary key,
nombre			varchar(20),
descripcion		varchar(100));

create table categoriaproducto(
producto 	int,
categoria	int,
primary key(producto,categoria),
foreign key	(producto) references producto(idproducto),
foreign key	(categoria) references categoria(idcategoria));