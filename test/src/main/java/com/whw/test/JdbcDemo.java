package com.whw.test;

import java.sql.*;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
//        System.out.println(connection.getCatalog());
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
//        preparedStatement.execute();
//        preparedStatement.executeQuery();
//        int delete_from_user = preparedStatement.executeUpdate("delete from user");
//        System.out.println(delete_from_user);
//        ResultSet resultSet = preparedStatement.getResultSet();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getString(2));
//            System.out.println(resultSet.getInt(3));
//        }
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet1 = statement.executeQuery("select * from user");
//        while (resultSet1.next()) {
//            System.out.println(resultSet1.getString(2));
//        }

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet catalogs = metaData.getCatalogs();
        while (catalogs.next()){
            System.out.println(catalogs.getString(1));
        }
    }
}
