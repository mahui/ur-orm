package com.memosea.orm.api;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mahui on 2017/3/6.
 */

class TableBean{
    private String columnName;
    private String columnAliasName;
    private String setMethodName;
    private String setMethodAliasName;

    public TableBean(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnAliasName() {
        String[] splitStr = columnName.split("_");

        String aliasName = splitStr[0];
        for(int i = 1; i < splitStr.length; i++){
            aliasName = aliasName + splitStr[i].substring(0,1).toUpperCase() + splitStr[i].substring(1);
        }
        return aliasName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSetMethodName() {
        return "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }

    public String getSetMethodAliasName() {
        String[] splitStr = columnName.split("_");

        String aliasName = "set";
        for(String s : splitStr){
            aliasName = aliasName + s.substring(0,1).toUpperCase() + s.substring(1);
        }
        return aliasName;
    }


}

public class TableUtil {

    Connection conn;
    private TableUtil(Connection conn){
        this.conn = conn;
    }
    public static TableUtil connect(Connection conn){
        return new TableUtil(conn);
    }
    public static TableUtil datasource(DataSource dataSource) throws SQLException {
        return new TableUtil(dataSource.getConnection());
    }
    public List<Map<String,Object>> query(String sql, Object... params) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(params != null && params.length > 0){
                for(int i = 1; i <= params.length; i++){
                    ps.setObject(i,params[i-1]);
                }
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
            while (rs.next()){
                Map<String,Object> map = new HashMap();
                for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
                    map.put(rsmd.getColumnName(i),rs.getObject(rsmd.getColumnName(i)));
                }
                result.add(map);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }

    public <T>T queryOne(String sql, Class clazz, Object... params) throws Exception {
        List<T> result = query(sql, clazz, params);
        if(result.size() > 0){
            return result.get(0);
        }
        return null;
    }


    public <T>List<T> query(String sql, Class clazz, Object... params) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(params != null && params.length > 0){
                for(int i = 1; i <= params.length; i++){
                    ps.setObject(i,params[i-1]);
                }
            }
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            List<TableBean> tbList = new ArrayList<TableBean>();
            for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
                tbList.add(new TableBean(rsmd.getColumnName(i)));
            }

            List<T> result = new ArrayList<T>();
            while (rs.next()){
                Object obj = clazz.newInstance();
                for(TableBean tableBean : tbList){
                    Object value = rs.getObject(tableBean.getColumnName());
                    Method setMethod = null;
                    try {
                        setMethod = clazz.getMethod(tableBean.getSetMethodName(),clazz.getDeclaredField(tableBean.getColumnName()).getType());
                    } catch (Exception e) {
                        try {
                            setMethod = clazz.getMethod(tableBean.getSetMethodAliasName(),clazz.getDeclaredField(tableBean.getColumnAliasName()).getType());
                        } catch (NoSuchMethodException e1) {
                            System.out.println("no method named " + tableBean.getSetMethodName() + " or " + tableBean.getSetMethodAliasName());
                        }
                    }
                    if(setMethod != null){
                        setMethod.invoke(obj,value);
                    }
                }
                result.add((T) obj);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InstantiationException e) {
            throw e;
        }
    }



    /**
     *
     * @param sql
     * @param params
     * @return lastInsertId
     */
    public Long save(String sql, Object... params) throws SQLException {
        Long lastId = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            if(params != null && params.length > 0){
                for(int i = 1; i <= params.length; i++){
                    ps.setObject(i,params[i-1]);
                }
            }
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                lastId = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lastId;
    }

    public void update(String sql, Object... params) throws SQLException {
        Long lastId = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            if(params != null && params.length > 0){
                for(int i = 1; i <= params.length; i++){
                    ps.setObject(i,params[i-1]);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String sql, Object... params) throws SQLException {
        Long lastId = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            if(params != null && params.length > 0){
                for(int i = 1; i <= params.length; i++){
                    ps.setObject(i,params[i-1]);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

}
