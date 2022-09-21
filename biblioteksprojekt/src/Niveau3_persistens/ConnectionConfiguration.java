package Niveau3_persistens;

import Niveau2_logik.TextUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {

    static TextUI textUI = new TextUI();

        static String pass = null;
        public static Connection getConnection() {

            if(pass == null){
                System.out.println("Enter password");
                pass = textUI.get();
            }

            Connection connection = null;
            String url = "jdbc:mysql://localhost:3306/Bibliotek?serverTimezone=CET&useSSL=false";
            String user = "root";
            String password = pass;


            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

}
