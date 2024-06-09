package com.atguigu.base;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author LiHongFei
 * @since 2024/6/9
 */
public class JDBCOperation {

    @Test
    public void testQuerySingleRowAndCol() throws Exception{
       // 1.注册驱动 (可以省略)

       // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

       // 3.预编译SQL语句得到PreparedStatement 对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as count from t_emp");

        // 4.执行SQL语句，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5.处理结果(如果自己明确只有一个结果，那么resultSet最少要做一次next的判断，才能拿到我们要的结果)
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            System.out.println(count);
        }

        // 6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testQuerySingleRow() throws Exception {
        // 1.注册驱动

        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

        // 3.预编译SQL语句得到PreparedStatement 对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_id = ?");

        // 4.为占位符赋值，然后执行，并接受结果
        preparedStatement.setInt(1,5);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5.处理结果
        while (resultSet.next()) {
            int emp_id = resultSet.getInt("emp_id");
            String emp_name = resultSet.getString("emp_name");
            double emp_salary = resultSet.getDouble("emp_salary");
            int emp_age = resultSet.getInt("emp_age");

            System.out.println(emp_id+"\t"+emp_name+"\t"+emp_salary+"\t"+emp_age);
        }

        // 6.资源释放
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testQueryMoreRow() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_age > ?");

        // 为占位符赋值，执行SQL语句，接受结果
        preparedStatement.setInt(1,25);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int emp_id = resultSet.getInt("emp_id");
            String emp_name = resultSet.getString("emp_name");
            double emp_salary = resultSet.getDouble("emp_salary");
            int emp_age = resultSet.getInt("emp_age");

            System.out.println(emp_id+"\t"+emp_name+"\t"+emp_salary+"\t"+emp_age);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_emp(emp_name,emp_salary,emp_age) values (?,?,?)");

        preparedStatement.setString(1,"rose");
        preparedStatement.setDouble(2,345.67);
        preparedStatement.setInt(3,37);

        int result = preparedStatement.executeUpdate();

        // 根据受影响的行数，得到成功或失败
        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?");

        preparedStatement.setDouble(1,99999.33);
        preparedStatement.setInt(2,6);

        int result = preparedStatement.executeUpdate();

        // 根据受影响的行数，得到成功或失败
        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/atguigu","root","123456");

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_emp WHERE emp_id = ?");

        preparedStatement.setInt(1,6);

        int result = preparedStatement.executeUpdate();

        // 根据受影响的行数，得到成功或失败
        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        preparedStatement.close();
        connection.close();
    }
}
