use poliventas;

insert into cuenta(usuario,contraseña,rol) values 
											("jluna","jluna123","Comprador"),
                                            ("mbanchon","mbanchon123","Comprador"),
                                            ("aneira","aneira123","Vendedor"),
                                            ("kalvear","kalvear123","Vendedor"),
                                            ("kmelgar","kmelgar123","Vendedor"),
                                            ("kjgomez","kjgomez123","Administrador"),
                                            ("yatienci","yatienci123","Administrador");
										
insert into persona values ("0900000001","Jocellyn","Luna","0900000011","jluna@espol.edu.ec","sur","201708001",0,false,"jluna");
insert into persona values ("0900000002","Melanie","Banchon","0900000022","mbanchon@espol.edu.ec","norte","201708002",0,false,"mbanchon");
insert into persona values ("0900000003","Andrea","Neira","0900000033","aneira@espol.edu.ec","norte","201608001",0,false,"aneira");
insert into persona values ("0900000004","Kamyla","Alvear","0900000044","kalvear@espol.edu.ec","Duran","201608002",0,false,"kalvear");
insert into persona values("0900000005","Karla","Melgar","0900000055","kmelgar@espol.edu.ec","sur","201608003",0,false,"kmelgar");
insert into persona values ("0900000006","Kevin","Gomez","0900000066","kjgomez@espol.edu.ec","norte","201508179",0,false,"kjgomez");
insert into persona values ("0900000007","Ysabel","Atiencia","0900000077","yatienci@espol.edu.ec","suroeste","201608005",0,false,"yatienci");
                            
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("Cubo 3x3",3,"Entrenimiento",10,5,"0900000004");
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("Audifonos Huawei",2,"Tecnologia",7.50,10,"0900000005");
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("Samsung J7",10,"Tecnologia",260,1,"0900000005");
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("SD 32gb",1,"Tecnologia",17.5,20,"0900000005");
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("Megaminx",3,"Entretenimiento",12.5,5,"0900000004");
insert into producto (nombre,tiempoMaxEntrega,categoria,costo,cantidadDisponible,vendedor)values ("Piraminx",3,"Entretenimiento",11.5,5,"0900000004");

insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000001",1,"Pendiente",1,"2019-01-19");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000002",1,"Pendiente",1,"2019-01-20");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000002",2,"Pendiente",1,"2019-01-20");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000001",5,"Pendiente",1,"2019-01-20");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000002",3,"Pendiente",1,"2019-01-20");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000001",4,"Pendiente",1,"2019-01-20");
insert into pedido(comprador,producto,estado,cantidad,fechaPedido)values("0900000002",4,"Pendiente",1,"2019-01-20");
