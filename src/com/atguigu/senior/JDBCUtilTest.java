package com.atguigu.senior;

import com.atguigu.senior.util.JDBCUtil;
import com.atguigu.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author LiHongFei
 * @since 2024/6/9
 */
public class JDBCUtilTest {

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        // CRUD

        JDBCUtil.release(connection);
    }

    @Test
    public void testJDBCV2() {
        /**
         * com.mysql.cj.jdbc.ConnectionImpl@4416d64f
         * com.mysql.cj.jdbc.ConnectionImpl@6bf08014
         * com.mysql.cj.jdbc.ConnectionImpl@5e3d57c7
         */
        /*Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);*/

        /**
         * com.mysql.cj.jdbc.ConnectionImpl@4416d64f
         * com.mysql.cj.jdbc.ConnectionImpl@4416d64f
         * com.mysql.cj.jdbc.ConnectionImpl@4416d64f
         */
        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }
}
