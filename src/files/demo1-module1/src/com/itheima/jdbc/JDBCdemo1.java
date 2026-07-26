package com.itheima.jdbc;

import java.sql.*;

public class JDBCdemo1 {
    public static void main(String[] args) throws SQLException {

        // 防止sql注入 preparedStatement


        String url = "jdbc:mysql://localhost:3306/itcast?serverTimezone=UTC";
        String user = "root";
        String password = "123456cc";
        Connection connection = DriverManager.getConnection(url, user, password);

        String username = "zsan";
        int pwd = 1;
        // 3 定义sql
        String sql = "select * from tb_user where id = ? and name = ?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, pwd);
        pst.setString(2, username);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        pst.close();
        connection.close();
    }
}
