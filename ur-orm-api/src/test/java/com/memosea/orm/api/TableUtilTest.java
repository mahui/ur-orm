package com.memosea.orm.api;

import com.memosea.orm.bean.Token;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

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
    public void reflactTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        System.out.println(Token.class.getDeclaredFields());
        for(Field field : Token.class.getDeclaredFields()){
            System.out.println(field.getName());
        }
        System.out.println(Token.class.getDeclaredField("id").getType());
    }

    @Test
    public void query() throws SQLException {
        System.out.println(TableUtil.connect(getConnection()).query("select * from token"));
    }
    @Test
    public void queryBeans() throws Exception {
        System.out.println(TableUtil.connect(getConnection()).query("select * from token",Token.class));
    }
    @Test
    public void save() throws SQLException {
        System.out.println(TableUtil.connect(getConnection()).save("insert into token values(null,?,?,null),(null,?,?,null),(null,?,?,null)","123",123,"123",123,"123",123));
    }
    @Test
    public void update() throws SQLException {
        TableUtil.connect(getConnection()).update("update token set access_token = ? where id = ?","10007",10);
    }
    @Test
    public void delete() throws SQLException {
        TableUtil.connect(getConnection()).delete("delete from token where id = ?",10);
    }
}
