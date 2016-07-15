package com.github.inza9hi.bigdata.h2;

import java.sql.*;
import java.util.Random;

/**
 * Created by lawulu on 16-7-5.
 */
public class H2Test {

    public static final   int column =10;
    public static final   int row =10000*10;


    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:tcp://localhost/mem:testmemdb", "sa", "");
        // add application code here

        insert(conn);

        conn.close();
    }

    static void query(Connection conn) throws Exception{

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select count(*) ID from reports where name11 is not null ");
        while(rs.next()) {
            System.out.println(rs.getInt("ID"));
        }
    }

    static void create(Connection conn) throws Exception {
        String sql ="CREATE TABLE reports_2(ID INT PRIMARY KEY";

        for (int i = 0; i < column; i++) {
            sql+= ",NAME"+i+" VARCHAR(10)";
        }
        sql+=");";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    static void insert(Connection conn) throws Exception {

        Statement stmt = conn.createStatement();
        for (int i = 0; i < row; i++) {
            String sql = "INSERT INTO reports_2 VALUES(";
            sql+=i+21530;
            for (int j = 0; j < column; j++) {
                Random r = new Random();
                double v = r.nextDouble();
                if(v<0.2){
                    sql+=",'Mem"+j+"'";
                }else {
                    sql += ",NULL ";
                }
            }
            sql+=")";
            stmt.executeUpdate(sql);

        }

        //String sql = "INSERT INTO TEST_MEM VALUES(1, 'Hello_Mem')"

    }
}
