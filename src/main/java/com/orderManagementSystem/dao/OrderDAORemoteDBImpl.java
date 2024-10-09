package com.orderManagementSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderManagementSystem.dao.models.Order;

@Component
public class OrderDAORemoteDBImpl implements OrderDAO {

    private static final String INSERT = "INSERT INTO orders (order_id, status, drink, size, customer_name, total_amount, payment_type, payment_status, error_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ONE = "SELECT order_id, status, drink, size, customer_name, total_amount, payment_type, payment_status, error_details FROM orders WHERE order_id=?";
    private static final String UPDATE = "UPDATE orders SET status = ?, drink = ?, size = ?, customer_name = ?, total_amount = ?, payment_type = ?, payment_status = ?, error_details = ? WHERE order_id = ?";

    // Inject DatabaseConnectionManager to get database connection
    @Autowired
    private DatabaseConnectionManager connectionManager;

    @Override
    public void insertOrder(Order order) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setLong(1, order.getOrderId());
        statement.setString(2, order.getStatus());
        statement.setString(3, order.getDrink());
        statement.setString(4, order.getSize());
        statement.setString(5, order.getName());
        //statement.setDouble(6, order.getTotalAmount());
        if (order.getTotalAmount() == null) {
            statement.setNull(6, java.sql.Types.DOUBLE);
        } else {
            statement.setDouble(6, order.getTotalAmount());
        }
        statement.setString(7, order.getPaymenttype());
        statement.setString(8, order.getPaymentstatus());
        statement.setString(9, order.getErrorDetails());
        statement.execute();
        connection.close();
    }

    @Override
    public Order getOrderById(Long orderId) {
        Order order = new Order();
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ONE);
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                order.setOrderId(rs.getLong("order_id"));
                order.setStatus(rs.getString("status"));
                order.setDrink(rs.getString("drink"));
                order.setSize(rs.getString("size"));
                order.setName(rs.getString("customer_name"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymenttype(rs.getString("payment_type"));
                order.setPaymentstatus(rs.getString("payment_status"));
                order.setErrorDetails(rs.getString("error_details"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, order.getStatus());
        statement.setString(2, order.getDrink());
        statement.setString(3, order.getSize());
        statement.setString(4, order.getName());
        statement.setDouble(5, order.getTotalAmount());
        statement.setString(6, order.getPaymenttype());
        statement.setString(7, order.getPaymentstatus());
        statement.setString(8, order.getErrorDetails());
        statement.setLong(9, order.getOrderId());
        statement.execute();
        connection.close();
    }
}
