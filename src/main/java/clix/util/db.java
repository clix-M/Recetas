package clix.util;

import java.sql.Connection;

public class db {
    // conexion a la base de datos mysql
/*
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/recetas?useSSL=false&serverTimezone=UTC";
            String PASS = "root";
            String USER = "root";
            conn = java.sql.DriverManager.getConnection(URL, USER, PASS);
        //   if (conn != null) System.out.println("Conexión a base de datos recetas... Ok");

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;

    }

 */

    // conexion a la base de datos postgresql
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            /*
            String URL = "jdbc:postgresql://ep-plain-boat-05926639-pooler.us-east-1.postgres.vercel-storage.com:5432/verceldb";
            String PASS = "mw9WMpn6RSkf";
            String USER = "default";

             */
            // usaremos el localhost
            String URL = "jdbc:postgresql://localhost:5432/recetas";
            String PASS = "dev";
            String USER = "postgres";
            conn = java.sql.DriverManager.getConnection(URL, USER, PASS);
         //    if (conn != null) System.out.println("Conexión a base de datos recetas... Ok");

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;

    }
}
