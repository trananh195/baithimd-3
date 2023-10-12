package service;

import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffService {
    public StaffService() {
    }

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
    public void saveStaff(Staff staff) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into staff(name, email, address, phone, salary, idDepartment) values (?, ?, ?, ?, ?, ?)");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getMail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setInt(4, staff.getPhone());
            preparedStatement.setDouble(5, staff.getSalary());
            preparedStatement.setInt(6, staff.getIdDepartment());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateStaff(Staff staff) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update staff set name = ?, email = ?, address = ?, phone = ?, salary = ?, idDepartment = ? where id = ?");
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getMail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setInt(4, staff.getPhone());
            preparedStatement.setDouble(5, staff.getSalary());
            preparedStatement.setInt(6, staff.getIdDepartment());
            preparedStatement.setInt(7,staff.getId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStaff(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from staff where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Staff> findAll() {
        List<Staff> staffs = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staff");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int phone = rs.getInt("phone");
                double salary = rs.getDouble("salary");
                int idDepartment  = rs.getInt("idDepartment");
                staffs.add(new Staff(id, name, email, address,phone, salary, idDepartment));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffs;
    }
    public Staff findById(int id) {
        Staff staff = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staff where id = ?");
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int phone = rs.getInt("phone");
                double salary = rs.getDouble("salary");
                int idDepartment  = rs.getInt("idDepartment");
                staff = new Staff(id, name, email, address,phone, salary, idDepartment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }
    public List<Staff> findByName(String nameSearch) {
        List<Staff> staffs = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staff where name like ?");
            preparedStatement.setString(1,"%" + nameSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int phone = rs.getInt("phone");
                double salary = rs.getDouble("salary");
                int idDepartment  = rs.getInt("idDepartment");
                staffs.add(new Staff(id, name, email, address,phone, salary, idDepartment));
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
        return staffs;
    }
}
