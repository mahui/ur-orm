# ur-orm
> ur-orm —— your orm，拿来即用。  
> 简单的封装。目的只有一个，Java 查询 DB 不再有 1,2,3... 只需要一个函数  
> 当然了，这又是一个轮子

# How To Use ?

```
public void query() throws SQLException {
    System.out.println(TableUtil.connect(getConnection()).query("select * from token where id = ?",0));
}
public void save() throws SQLException {
    System.out.println(TableUtil.connect(getConnection()).save("insert into token values(null,?,?,null),(null,?,?,null),(null,?,?,null)","123",123,"123",123,"123",123));
}
public void update() throws SQLException {
    TableUtil.connect(getConnection()).update("update token set access_token = ? where id = ?","10007",10);
}
public void delete() throws SQLException {
    TableUtil.connect(getConnection()).delete("delete from token where id = ?",10);
}
```

# TO-DO
 - 查询方法加入数据库列与自定义对象的自动映射（约定优于配置）。目前只有返回 Map 的方法
 - 支持插入对象
 - 更友好的批量插入的支持
