package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OperateDataBase {
    public static void main(String[] args) throws Exception {
        // JDBC, 操作数据库入门

        // 1、注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2 获取连接
        // zone error 在数据库后面加?serverTimezone=UTC
        String url = "jdbc:mysql://localhost:3306/itcast?serverTimezone=UTC";
        String user = "root";
        String password = "123456cc";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3 定义sql
        String sql = "update tb_user set money = 1200 where id = 1;";
        String sql2 = "select * from tb_user;";

        // 4 获取执行sql的对象
        Statement statement = connection.createStatement();         // 受影响的行数

        // 5 执行sql
//        int count = statement.executeUpdate(sql);
        ResultSet resultSet = statement.executeQuery(sql2);
        // 6 处理结果
//        System.out.println(count);

        ArrayList<Account> list = new ArrayList<>();
        // resultSet.next()判断是否是有效行并移动一行
        while (resultSet.next()) {
            Account account = new Account();
            String name = resultSet.getString("name");  // 获取数据
            String sex = resultSet.getString("gender");
            int age = resultSet.getInt("age");
            int id = resultSet.getInt("id");
            int money = resultSet.getInt("money");
//            System.out.println(name + "-" + sex + "-" + age + "-" + money);

            account.setId(id);
            account.setAge(age);
            account.setName(name);
            account.setSex(sex);
            account.setMoney(money);

            list.add(account);
        }

//        System.out.println(list);
        for (Account account : list) {
            System.out.println(account);
        }

//        list.forEach(a -> {
//            System.out.println(a);
//        });
        // 7 释放资源
        statement.close();
        resultSet.close();
        connection.close();

    }

}
