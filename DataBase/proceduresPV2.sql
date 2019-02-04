use poliventas;
delimiter $$
create procedure obtenerCedula(in us varchar(15),  out cedulaout varchar(10), out nombresout varchar(30),out apellidosout	varchar(30))
begin
select cedula , nombres ,apellidos into cedulaout, nombresout, apellidosout
from persona
where usuario= us ;
end $$


create procedure obtenerID(in nompro varchar(15), out id int)
begin
select idproducto into id from producto where nombre=nompro;
end $$


create procedure obtenerIDestrategia(in tipop varchar(20), out idout int)
begin
select id into idout from tipodepago where tipop=descripcion;
end $$

create procedure eliminarCuenta(
in us	varchar(15))
begin
update cuenta set eliminado = true  where usuario=us;
end$$

create procedure eliminarProducto(
in id	int)
begin
update producto set eliminado=true  where id=idproducto;
end$$

create procedure modificarDatosCuenta(
in usuarioin varchar(15),
in contrasenain varchar(15))
begin
update cuenta
set usuario=usuarioin, contraseña=contrasenain
where usuario=usuarioin;
end$$

create procedure modificarUsuario(
in cedulain		varchar(10) ,
in nombresin		varchar(30),
in apellidosin	varchar(30),
in numeroin		varchar(10),
in correoin		varchar(25),
in direccionin	varchar(50),
in matriculain 	varchar(10),
in usuarioin		varchar(15))
begin
update persona
set cedula=cedulain, nombres=nombresin,apellidos=apellidosin,numero=numeroin,correo=correoin,direccion=direccionin,
	matricula=matriculain,usuario=usuarioin
where cedula=cedulain;
end$$

create procedure modificarProducto(
in nombrein			varchar(15),
in tiempoMaxEntregain int,
in categoriain varchar(100),
in costoin				double,
in cantidadDisponiblein	int,
in vendedorin			varchar(10))
begin
update producto
set nombre=nombrein, tiempoMaxEntrega=tiempoMaxEntregain,categoria=categoriain,costo=costoin,
	cantidadDisponible=cantidadDisponiblein, vendedor=vendedorin
where nombre=nombrein;
end$$


create procedure getVendedor(
in cedulain		varchar(10) ,
out nombresin		varchar(30),
out apellidosin	varchar(30),
out numeroin		varchar(10),
out correoin		varchar(25),
out direccionin	varchar(50),
out matriculain 	varchar(10),
out saldoin		double,
out usuarioin		varchar(15))
begin
	select nombres, apellidos, numero, correo, direccion, matricula, saldo, usuario
    into nombresin, apellidosin, numeroin, correoin, direccionin, matriculain, saldoin, usuarioin from persona where cedulain=cedula;
end$$

create procedure getProducto(
in idin		int ,
out nombrein		varchar(30),
out tiempoin	int,
out categoriain		varchar(10),
out costoin		double,
out disponiblein	int)
begin
	select nombre, tiempoMaxEntrega, categoria, costo, cantidadDisponible
    into nombrein, tiempoin, categoriain, costoin, disponiblein from producto where idin=idproducto;
end$$

create procedure AddNotificacion(
in cedulain		varchar(10) )
begin
	if(cedulain not in (Select vendedor from Notificaciones)) then 
		Insert into Notificaciones(vendedor) values (cedulain);
	else
		update Notificaciones set cantidad=cantidad+1  where cedulain=vendedor;
	end if;
end$$

create procedure RemoveNotificacion(
in cedulain		varchar(10) )
begin
	if(cedulain not in (Select vendedor from Notificaciones)) then 
		Insert into Notificaciones(vendedor) values (cedulain);
	else
		update Notificaciones set cantidad=0 where cedulain=vendedor;
	end if;
end$$


create procedure getNotificacion(
in cedulain		varchar(10),
out cantidadout int)
begin
	select cantidad into cantidadout from Notificaciones where cedulain=vendedor;
end$$

create procedure getPedidosPendientes()
begin
	select comprador, producto, cantidad, fechaPedido, tipoPago from pedido where estado="Pendiente";
end$$

