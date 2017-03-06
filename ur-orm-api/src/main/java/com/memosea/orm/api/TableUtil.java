package com.memosea.orm.api;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mahui on 2017/3/6.
 */
public class TableUtil {

    Connection conn;
    private TableUtil(Connection conn){
        this.conn = conn;
    }
    public static TableUtil connect(Connection conn){
        return new TableUtil(conn);
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
            int resultSize = rs.getFetchSize();
            if(resultSize > 0){
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
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
