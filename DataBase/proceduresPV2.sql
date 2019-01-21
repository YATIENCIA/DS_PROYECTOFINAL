use poliventas;
delimiter $$
create procedure obtenerCedula(in us varchar(15),  out cedulaout varchar(10), out nombresout varchar(30),out apellidosout	varchar(30))
begin
select cedula , nombres ,apellidos into cedulaout, nombresout, apellidosout
from persona
where usuario= us ;
end $$


create procedure eliminarCuenta(
in us	varchar(15))
begin
update cuenta set eliminado = true  where usuario=us;
end$$

create procedure eliminarProducto(
in id	varchar(15))
begin
update producto set eliminado=true  where id=idproducto;
end$$
