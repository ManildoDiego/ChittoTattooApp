DROP DATABASE IF EXISTS chittotattoobd;
CREATE DATABASE chittotattoobd;
USE chittotattoobd;

CREATE TABLE personal (
    id int auto_increment,
    nombre VARCHAR(255),
    direccion VARCHAR(255),
    telefono int,
    constraint PK_personal PRIMARY KEY (id)
);

CREATE TABLE turnos (
    id int auto_increment,
    fecha datetime,
    detalles text,
    CONSTRAINT PK_turnos PRIMARY KEY (id)
);

CREATE TABLE facturas (
    id int auto_increment,
    monto float,
    fecha_emision DATETIME,
    personal int,
    CONSTRAINT PK_facturas PRIMARY KEY (id),
    CONSTRAINT FK_facturas FOREIGN KEY (personal) REFERENCES personal(id)
);

DELIMITER //

CREATE PROCEDURE insertar_datos_p (IN Pnombre VARCHAR(255),IN Pdireccion VARCHAR(255),IN Ptelefono int)
BEGIN
    INSERT INTO personal(nombre,direccion,telefono)
    VALUES (Pnombre,Pdireccion,Ptelefono);
END //

DELIMITER ;

--Procedimiento para insertar datos en la tabla turnos
DELIMITER //

CREATE PROCEDURE insertar_datos_t (IN Pfecha DATETIME, IN Pdetalles text)
BEGIN
    INSERT INTO turnos(fecha,detalles)
    VALUES (Pfecha,Pdetalles);
END //

DELIMITER ;

--Procedimiento para insertar datos en la tabla facturas
DELIMITER //

CREATE PROCEDURE insertar_datos_f (IN Pmonto FLOAT,IN Ppersonal int)
BEGIN
    INSERT INTO facturas(monto,fecha_emision,personal)
    VALUES (Pmonto,CURRENT_date(),Ppersonal);
END //

DELIMITER ;

--nombre del personal
DELIMITER //

CREATE PROCEDURE nombre_personal (IN Pid int)
BEGIN
    SELECT nombre FROM personal WHERE id=Pid;
END //

DELIMITER ;

--fecha del turno
DELIMITER //

CREATE PROCEDURE fecha_turno (IN Pid int)
BEGIN
    SELECT fecha FROM turnos WHERE id=Pid;
END //

DELIMITER ;

--detalles del turno
DELIMITER //

CREATE PROCEDURE detalles_turno (IN Pid int)
BEGIN
    SELECT detalles FROM turnos WHERE id=Pid;
END //

DELIMITER ;

--fecha emision facturas
DELIMITER //

CREATE PROCEDURE fecha_emision_f (IN Pid int)
BEGIN
    SELECT fecha_emision FROM facturas WHERE id=Pid;
END //

DELIMITER ;

--monto facturas
DELIMITER //

CREATE PROCEDURE monto_f (IN Pid int)
BEGIN
    SELECT monto FROM facturas WHERE id=Pid;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtenerIdPersonal(IN Pnombre VARCHAR(255))
BEGIN
    SELECT id FROM personal WHERE nombre = Pnombre;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerTurnos()
BEGIN
    SELECT * FROM turnos;
END //

DELIMITER ;

