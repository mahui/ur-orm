package com.memosea.orm.api;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mahui on 2017/3/6.
 */
public class TableUtilTest {

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wechat","root","root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Test
    public void query(){
        System.out.println(TableUtil.connect(getConnection()).query("select * from token where id = ?",0));
    }
    @Test
    public void save(){
        System.out.println(TableUtil.connect(getConnection()).save("insert into token values(null,?,?,null),(null,?,?,null),(null,?,?,null)","123",123,"123",123,"123",123));
    }
    @Test
    public void update(){
        TableUtil.connect(getConnection()).update("update token set access_token = ? where id = ?","10007",10);
    }
    @Test
    public void delete(){
        TableUtil.connect(getConnection()).delete("delete from token where id = ?",10);
    }
}
