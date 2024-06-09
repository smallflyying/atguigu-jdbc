package com.atguigu.base;

import java.sql.*;
import java.util.Scanner;

/**
 * @author LiHongFei
 * @since 2024/6/9
 */
public class JDBCPrepared {

    public static void main(String[] args) throws Exception {
        // 1.注册驱动 (可以省略)

        // 2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu?characterEncoding=UTF-8","root","123456");

        // 3.获取执行SQL语句对象
//        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp WHERE emp_name = ?");

        System.out.println("请输入员工姓名: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        // 4.编写SQL语句，并执行，接收返回的结果
//        String sql = "SELECT emp_id,emp_name,emp_salary,emp_age from t_emp WHERE emp_name = '"+name+"'";
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5.处理结果，遍历resultSet结果集
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        // 6.释放资源 (先开后关原则)
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
