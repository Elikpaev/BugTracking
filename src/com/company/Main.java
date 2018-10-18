package com.company;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Scanner scan = new Scanner(System.in);
        System.out.println("press 1 - login");
        System.out.println("press 2 - registration");
        int menu = scan.nextInt();
        switch (menu){
            case 1: signIn();
            break;
            case 2: signUp();
            break;
            default:
                System.out.println("wrong case");
                break;
        }

    }
    public static void signUp() throws SQLException, ClassNotFoundException{
        Scanner scan = new Scanner(System.in);
        String userName = "root";
        String password = "112896elik";
        String url = "jdbc:mysql://localhost:3306/bugtracking";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(url, userName, password);
            Statement statement = conn.createStatement()){
            System.out.println("enter login");
            String login = scan.nextLine();
            System.out.println("enter pass");
            String pass = scan.nextLine();
            statement.executeUpdate("insert into Users (login, password) VALUES ('"+login+"', '"+pass+"')");
            System.out.println("registration complite");
            System.out.println("your login - " + login +";");
            System.out.println("your pass - " + pass + ";");
        }
    }
    public static void signIn() throws ClassNotFoundException, SQLException{
        String userName = "root";
        String password = "112896elik";
        String url = "jdbc:mysql://localhost:3306/bugtracking";
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Enter u login");
        Scanner scan = new Scanner(System.in);
        String login = scan.nextLine();
        try (Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stat = conn.createStatement()){
            ResultSet resultSet = stat.executeQuery("SELECT * from users ");
            while (resultSet.next()){
                String chekLogin = resultSet.getString("login");
          //      System.out.println(chekLogin);
               if (login.equals(chekLogin)){
                   System.out.println("hi " + login);
                   System.out.println("Enter u password");
                   String pass = scan.nextLine();
                   ResultSet rs2 = stat.executeQuery("SELECT password from users where login = '"+chekLogin+"' " );
                   while (rs2.next()){
                       String chekPass = rs2.getString(1);
                       //System.out.println(chekPass);
                       if (pass.equals(chekPass)){
                           System.out.println("вітаю ви зайшли");
                       } else System.out.println("Неправильний пароль");
                   }

                   break;
                }


            }

        }
    }
}
