use poliventas;

create table busqueda(
idProd int,
numero int auto_increment,
primary key(numero),
foreign key	(idProd) references producto(idproducto));

drop procedure busquedaProd;
delimiter $$
create procedure busquedaProd(in busqueda varchar(100))
begin 
select nombre,categoria,  costo, cantidadDisponible, tiempoMaxEntrega
from producto

where (nombre like CONCAT('%', busqueda, '%') or categoria like CONCAT('%', busqueda, '%')) and eliminado=false;

insert into busqueda(idProd) select idproducto from producto

where (nombre like CONCAT('%', busqueda, '%') or categoria like CONCAT('%', busqueda, '%')) and eliminado=false;

end$$

delimiter ;

select nombre, costo, sq.cantidad as cantidadBusquedas
from producto
join (select count(numero) as cantidad, idProd
		from busqueda
        group by idProd) sq on producto.idproducto=sq.idProd
order by cantidad desc
limit 15 ;



