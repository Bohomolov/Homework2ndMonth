package secondmounthlastpractice.dao;

import secondmounthlastpractice.dao.config.Config;

import java.sql.*;

public class PostgreSQL {

    public void executeUpdate(String name) {
        try {
            Class.forName(Config.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(Config.URL, Config.LOGIN, Config.PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(Config.Query.UPDATE);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void executeQueryAllCustomerByProductName() {
        try {
            Class.forName(Config.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(Config.URL, Config.LOGIN, Config.PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Config.Query.SELECT_QUERY_ALL_CUSTOMER_BY_PRODUCT_NAME);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void executeQueryAllProductWithCustomerName() {
        try {
            Class.forName(Config.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(Config.URL, Config.LOGIN, Config.PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Config.Query.SELECT_QUERY_ALL_PRODUCTS_WITH_CUSTOMER_NAME);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
