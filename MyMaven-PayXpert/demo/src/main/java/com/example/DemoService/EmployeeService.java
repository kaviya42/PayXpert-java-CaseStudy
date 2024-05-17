package com.example.DemoService ;

//import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import com.example.model.*;
import com.example.DemoDao.*;
import com.example.Exception.*;

public class EmployeeService  {
    Scanner sc;
    EmployeeDao edao;

    public EmployeeService() {
        sc = new Scanner(System.in);
        try {
            edao = new EmployeeDao();
        } catch (DatabaseConnectionException ex) {
            System.out.println(ex.getMessage());
           
        }
    }
    public void ShowEmployeeById(){
        System.out.println("Enter Employee ID:");
         int employeeID = sc.nextInt();
         System.out.println();
        try {
            edao.selectEmployee(employeeID);
            
        } catch (EmployeeNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addEmployee() {
        try {
        Employee e = new Employee();
        System.out.println("Enter EmployeeID:");
        e.setEmployeeID(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter First Name:");
        e.setFirstName(sc.nextLine());
        System.out.println("Enter Last Name:");
        e.setLastName(sc.nextLine());
        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
        e.setDateOfBirth(LocalDate.parse(sc.nextLine()));
        System.out.println("Enter Gender:");
        e.setGender(sc.nextLine());
        System.out.println("Enter Email:");
        e.setEmail(sc.nextLine());
        System.out.println("Enter Phone Number:");
        e.setPhoneNumber(sc.nextLine());
        System.out.println("Enter Address:");
        e.setAddress(sc.nextLine());
        System.out.println("Enter Position:");
        e.setPosition(sc.nextLine());
        System.out.println("Enter Joining Date (YYYY-MM-DD):");
        e.setJoiningDate(LocalDate.parse(sc.nextLine()));
        System.out.println("Enter Termination Date (YYYY-MM-DD):");
        e.setTerminationDate(LocalDate.parse(sc.nextLine()));

        
            edao.add(e);
            
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEmployeePosition() {
        try {
        System.out.println("Enter Employee ID:");
        int employeeID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter New Position:");
        String position = sc.nextLine();
        
        edao.update(position, employeeID);
            System.out.println("Employee position updated successfully.");
        } catch (EmployeeNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeEmployee() {
        System.out.println("Enter Employee ID:");
        int employeeID = sc.nextInt();
        try {
            edao.remove(employeeID);
            System.out.println("Employee removed successfully.");
        } catch (EmployeeNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void displayEmployees() {
        edao.show();
    }
}
