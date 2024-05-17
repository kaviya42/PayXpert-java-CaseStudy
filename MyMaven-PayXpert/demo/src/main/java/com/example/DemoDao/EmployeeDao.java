package com.example.DemoDao;
import java.sql.Date;
import java.sql.*;

import java.util.*;
import com.example.Connect.*;
import com.example.Exception.*;
import com.example.model.*;;

public class EmployeeDao extends IEmployeeService {
    private Connection con;
    private PreparedStatement stat;

    public EmployeeDao() throws DatabaseConnectionException {
        con = DataConnect.getConnect();
    }

    public void selectEmployee(int employeeID) throws EmployeeNotFoundException {
        try {
            stat = con.prepareStatement("SELECT EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate from Employee where EmployeeID = ?");
            stat.setInt(1, employeeID);
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()) {
                System.out.println("EmployeeID: " + resultSet.getInt("EmployeeID"));
                System.out.println("FirstName: " + resultSet.getString("FirstName"));
                System.out.println("LastName: " + resultSet.getString("LastName"));
                System.out.println("DateOfBirth: " + resultSet.getDate("DateOfBirth"));
                System.out.println("Gender: " + resultSet.getString("Gender"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("PhoneNumber: " + resultSet.getString("PhoneNumber"));
                System.out.println("Address: " + resultSet.getString("Address"));
                System.out.println("Position: " + resultSet.getString("Position"));
                System.out.println("JoiningDate: " + resultSet.getDate("JoiningDate"));
                System.out.println("TerminationDate: " + resultSet.getDate("TerminationDate"));
            }
        } catch (SQLException ex) {
            throw new EmployeeNotFoundException("Error showing employee " + ex.getMessage());
        }
    }

    public void add(Employee e) throws InvalidInputException {
        try {
            stat = con.prepareStatement("INSERT INTO Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stat.setInt(1, e.getEmployeeID());
            stat.setString(2, e.getFirstName());
            stat.setString(3, e.getLastName());
            stat.setDate(4, Date.valueOf(e.getDateOfBirth()));
            stat.setString(5, e.getGender());
            stat.setString(6, e.getEmail());
            stat.setString(7, e.getPhoneNumber());
            stat.setString(8, e.getAddress());
            stat.setString(9, e.getPosition());
            stat.setDate(10, Date.valueOf(e.getJoiningDate()));
            stat.setDate(11, Date.valueOf(e.getTerminationDate()));

            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new InvalidInputException("Error adding employee " + ex.getMessage());
        }
    }

    public void update(String position, int employeeID) throws EmployeeNotFoundException {
        try {
            stat = con.prepareStatement("UPDATE Employee SET Position = ? WHERE EmployeeID = ?");
            stat.setString(1, position);
            stat.setInt(2, employeeID);
            stat.executeUpdate();
            System.out.println("Employee with ID " + employeeID + " position updated to " + position);
        } catch (SQLException e) {
            throw new EmployeeNotFoundException("Error updating employee position: " + e.getMessage());
        }
    }

    public void remove(int employeeID) throws EmployeeNotFoundException {
        try {
            stat = con.prepareStatement("delete from employee where EmployeeID =? ");
            stat.setInt(1, employeeID);
            stat.executeUpdate();

            System.out.println("Employee with ID " + employeeID + " removed successfully.");

        } catch (SQLException e) {
            throw new EmployeeNotFoundException("Error removing employee: " + e.getMessage());
        }
    }

    public void show() {
        List<Employee> employees = new ArrayList<>();
        try {
            stat = con.prepareStatement("SELECT * FROM Employee");
            ResultSet resultSet = stat.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setDateOfBirth(resultSet.getDate("DateOfBirth").toLocalDate());
                employee.setGender(resultSet.getString("Gender"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setPosition(resultSet.getString("Position"));
                employee.setJoiningDate(resultSet.getDate("JoiningDate").toLocalDate());
                employee.setTerminationDate(resultSet.getDate("TerminationDate").toLocalDate());
                employees.add(employee);
            }

            for (Employee employee : employees) {
                System.out.println("EmployeeID: " + employee.getEmployeeID());
                System.out.println("FirstName: " + employee.getFirstName());
                System.out.println("LastName: " + employee.getLastName());
                System.out.println("DateOfBirth: " + employee.getDateOfBirth());
                System.out.println("Gender: " + employee.getGender());
                System.out.println("Email: " + employee.getEmail());
                System.out.println("PhoneNumber: " + employee.getPhoneNumber());
                System.out.println("Address: " + employee.getAddress());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("JoiningDate: " + employee.getJoiningDate());
                System.out.println("TerminationDate: " + employee.getTerminationDate());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee data " + e.getMessage());
        }
    }
}
