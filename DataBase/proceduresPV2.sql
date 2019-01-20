use poliventas;
delimiter $$
create procedure obtenerCedula(in us varchar(15),  out cedulaout varchar(10), out nombresout varchar(30),out apellidosout	varchar(30))
begin
select cedula , nombres ,apellidos into cedulaout, nombresout, apellidosout
from persona
where usuario= us ;
end $$

