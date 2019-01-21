use poliventas;
delimiter $$
create procedure obtenerRol(in us varchar(15), in pass varchar(15), out rolout varchar(15))
begin
select rol into rolout
from cuenta
where usuario= us and contraseña= pass and eliminado=false;
end $$

create procedure historialComprasEstado(in cedu varchar(10), in estadoin varchar(15))
begin 
select p.nombre , sq.cantidad
from producto p 
join (select * 
	  from pedidos
      where comprador=cedu and estado=estadoin) sq on sq.producto=p.idproducto;
end $$

create procedure historialVentas(in cedu varchar(10), in estadoin varchar(15))
begin 
select p.nombre as nombre, p.costo as precio, p.descripcion as descrip, ped.cantidad as cantidad 
from producto p
join pedidos ped on ped.producto=p.idproducto
where p.vendedor=cedu and ped.estado=estadoin;
end $$

create procedure busquedaProd(in busqueda varchar(100))
begin 
select nombre, descripcion, costo, cantidadDisponible, tiempoMaxEntrega, sq.calificacion as calificacion
from producto
join( select avg(calificacion) as calificacion, producto
		from calificacionproducto
        group by producto) sq on sq.producto= producto.idproducto 
where (nombre like CONCAT('%', busqueda, '%') or descripcion like CONCAT('%', busqueda, '%')) and eliminado=false;
end$$

create procedure misProd(in cedula varchar(10))
begin 
select nombre, descripcion, costo, cantidadDisponible
from producto
where vendedor=cedula and eliminado=false;
end$$

create procedure ingresarCuenta(in usuarioin varchar(15),in contraseñain 	varchar(15),in rolin	varchar(15))
begin 
insert into cuenta(usuario,contraseña,rol) values(usuarioin,contraseñain,rolin);
end$$

create procedure ingresarPersona(
in cedulain		varchar(10) ,
in nombresin		varchar(30),
in apellidosin	varchar(30),
in numeroin		varchar(10),
in correoin		varchar(25),
in direccionin	varchar(50),
in matriculain 	varchar(10),
in saldoin		double,
in usuarioin		varchar(15))
begin
insert into persona(cedula, nombres,apellidos,numero,correo,direccion,matricula,saldo,usuario) values (cedulain, nombresin,apellidosin,numeroin,correoin,direccionin,matriculain,saldoin,usuarioin);
end$$

create procedure ingresarProducto(
in idproductoin			int,
in nombrein			varchar(15),
in descripcionin			varchar(100),
in costoin				double,
in cantidadDisponiblein	int,
in vendedorin			varchar(10))
begin
insert into producto(idproducto, nombre, descripcion, costo, cantidadDisponible, vendedor) 
values (idproductoin, nombrein, descripcionin, costoin, cantidadDisponiblein, vendedorin);
end$$

create procedure ingresarPedido(
in compradorin		varchar(10),
in productoin		int,
in cantidadin 		int,
in estadoin			varchar(15),
in tipopagoin		int)
begin
insert into pedido(comprador, producto, cantidad, estado, tipopago)
values(compradorin, productoin, cantidadin, estadoin, tipopagoin);
end$$

create procedure ingresarTipopago(
in descripcionin	varchar(20))
begin
insert into tipopago(descripcion)
values (descripcionin);
end$$

create procedure ingresarCalificacionproducto(
in compradorin		varchar(10),
in productoin		int,
in calificacion 	int)
begin
insert into calificacionproducto(comprador, producto, calificacion)
values(compradorin, productoin, calificacionin);
end$$

create procedure ingresarCalificacionvendedor(
in compradorin		varchar(10),
in productoin		int,
in calificacion 	int)
begin
insert into calificacionproducto(comprador, producto, calificacion)
values(compradorin, productoin, calificacionin);
end$$

create procedure ingresarCategoria(
in nombrein			varchar(20),
in descripcionin		varchar(100))
begin
insert into categoria(nombre, descripcion)
values(nombrein, descripcionin);
end$$

create procedure ingresarCategoriaproducto(
in productoin 	int,
in categoriain	int)
begin
insert into categoriaproducto(producto, categoria)
values(productoin. categoriain);
end$$