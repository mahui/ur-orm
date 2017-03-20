package com.memosea.orm.api;

import com.alibaba.fastjson.JSON;
import com.memosea.orm.bean.Message;
import com.memosea.orm.bean.Token;
import com.oracle.javafx.jmx.json.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mahui on 2017/3/6.
 */

public class TableUtilTest {

    Connection connection = null;

    public Connection getConnection(){
        if(connection != null){
            return connection;
        }
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
    public void query() throws Exception {
        System.out.println(TableUtil.connect(getConnection()).query("select * from token"));
        System.out.println(TableUtil.connect(getConnection()).query("select * from message"));
    }
    @Test
    public void queryBeans() throws Exception {
        System.out.println(TableUtil.connect(getConnection()).query("select * from token",Token.class));
        System.out.println(TableUtil.connect(getConnection()).query("select * from message where msgContent->'$.abc'=?", Message.class,"efg"));
    }
    @Test
    public void save() throws SQLException {
        System.out.println(TableUtil.connect(getConnection()).save("insert into token values(null,?,?,null),(null,?,?,null),(null,?,?,null)","123",123,"123",123,"123",123));
        Map<String,Object> data = new HashMap();
        data.put("abc","efghjk");
        data.put("def","ddd");
        System.out.println(TableUtil.connect(getConnection()).save("insert into message values(null,?,?,?,null)","123","text", JSON.toJSON(data).toString()));
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
