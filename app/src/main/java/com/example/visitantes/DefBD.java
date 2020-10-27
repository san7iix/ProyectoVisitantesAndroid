package com.example.visitantes;

public class DefBD {

    public static final String nameDB = "VisitantesDb";
    //Tablas
    public static final String tablaVisitantes = "visitantes";
    public static final String tablaUsuarios = "usuarios";
    //variables
        //Tabla usuarios
    public static final String usuario_usuario = "usuario";
    public static final String usuario_password = "password";
        //Tabla visitantes
    public static final String visitantes_identificacion = "identificacion";
    public static final String visitantes_nombre = "nombre";
    public static final String visitantes_apartamento = "apartamento";
    public static final String visitantes_tipo_visitante = "tipo";
    public static final String visitantes_fecha = "fecha";


    //Querys

    public static final String query1 = "CREATE TABLE IF NOT EXISTS " + DefBD.tablaUsuarios + " ( "
            +DefBD.usuario_usuario + " TEXT PRIMARY KEY, "
            +DefBD.usuario_password+" TEXT );";

    public static final String query2 = "CREATE TABLE IF NOT EXISTS "+ DefBD.tablaVisitantes +" ("+
            DefBD.visitantes_identificacion + " text primary key,"+
            DefBD.visitantes_nombre + " text,"+
            DefBD.visitantes_apartamento + " text,"+
            DefBD.visitantes_tipo_visitante + " text,"+
            DefBD.visitantes_fecha + " DATETIME DEFAULT CURRENT_TIMESTAMP"+
            ");";

}
