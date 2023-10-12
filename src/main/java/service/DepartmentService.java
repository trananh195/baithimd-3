package service;

import model.Department;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    public DepartmentService() {}
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baithimd3?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return connection;
    }
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from department");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }
    public Department findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from department where id = ?");
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                return new Department(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
