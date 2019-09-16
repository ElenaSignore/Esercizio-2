/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.esercizio2.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utente
 */
public class DbConnector {
    private static final String URL="jdbc:mysql://localhost:3306/world";
    private static final String USER="admin";
    private static final String PASSWORD="admin";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SELECT_CITY="select * from city where Name=(?);";
    private static final String SELECT_CITIES="select Name from city where CountryCode=(?);";
    private static String countryCode;
    private static int population;
    private static String district;
    
    public static String getCountryCode(){
        return countryCode;
    }
    
    public static int getPopulation(){
        return population;
    }
    
    public static String getDistrict(){
        return district;
    }
    
    public static Connection connetti() throws SQLException{
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn=DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }
    
    public static ArrayList<String> getCitta(String nome){
        ArrayList<String> citta= new ArrayList();
    
    try(Connection conn=connetti();
                PreparedStatement pstmt=conn.prepareStatement(SELECT_CITY);
            PreparedStatement pstmt2=conn.prepareStatement(SELECT_CITIES);){
            pstmt.setString(1, nome);
            ResultSet rs=pstmt.executeQuery();
            rs.next();
            countryCode=rs.getString("CountryCode");
            population=rs.getInt("Population");
            district=rs.getString("District");
            System.out.println(district);
            pstmt2.setString(1, countryCode);
            rs=pstmt2.executeQuery();
            while(rs.next()){
                citta.add(rs.getString("Name"));
            }
        
    }   catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    return citta;
}
    
}