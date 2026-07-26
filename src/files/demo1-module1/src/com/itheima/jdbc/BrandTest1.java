package com.itheima.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class BrandTest1 {
    public static void main(String[] args) throws SQLException {

        // 1 查询
//        operate(0);

        // 2 增
        operate(1);
        // 2 删除
//        operate(2);
        // 2 改
//        operate(3);
    }

    private static void operate(int type) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/itcast?serverTimezone=UTC";
        String user = "root";
        String password = "123456cc";

        Connection conn = DriverManager.getConnection(url, user, password);

        String sql1 = "select * from phone;";
        String sql2 = "insert into phone values (4, '三星', '三星电子', 4, 'boom', 4);";
        String sql3 = "update phone set brand_name = 'vivo' where ordered = 4;";
        String sql4 = "delete from phone where id = 4;";

        Statement statement = conn.createStatement();
        if (type != 0) {
            String updateSql = type == 1 ? sql2 : type == 2 ? sql4 : sql3;
            int i = statement.executeUpdate(updateSql);
            if (i > 0) {
                System.out.println("操作成功");
            } else {
                System.out.println("操失败");
            }
        }

        ResultSet res = statement.executeQuery(sql1);

        ArrayList<Brand> list = new ArrayList<>();

        while (res.next()) {
            Integer id = res.getInt("id");
            String brandName = res.getString("brand_name");
            String companyName = res.getString("company_name");
            Integer ordered = res.getInt("ordered");
            String description = res.getString("description");
            Integer status = res.getInt("status");

            Brand b = new Brand();
            b.setId(id);
            b.setBrandName(brandName);
            b.setCompanyName(companyName);
            b.setOrdered(ordered);
            b.setDescription(description);
            b.setStatus(status);

            list.add(b);
        }

        res.close();
        statement.close();

        list.forEach(s -> {
            System.out.println(s);
        });
    }
}
