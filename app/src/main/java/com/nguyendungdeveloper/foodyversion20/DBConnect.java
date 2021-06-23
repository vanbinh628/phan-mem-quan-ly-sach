package com.nguyendungdeveloper.foodyversion20;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnect {

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:6666/hello?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "root");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
