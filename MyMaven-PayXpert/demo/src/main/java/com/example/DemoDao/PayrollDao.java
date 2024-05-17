package com.example.DemoDao;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.example.Connect.*;
import com.example.Exception.*;

public class PayrollDao extends IPayrollService {
    private Connection con;
    private PreparedStatement stat;

    public PayrollDao() throws DatabaseConnectionException {
        con = DataConnect.getConnect();
    }

   public void GeneratePayroll(int employeeId,int payrollId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
        try {
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            int extraDays = 0;

            if (totalDays > 30) {
                extraDays = (int) (totalDays - 30);
            } else if (totalDays < 30) {
                extraDays = (int) (30 - totalDays);
            }

            double basicSalary = 20000.0;
            double perDaySalary = 200.0;
            double overtimePay = 0;
            double deductions = 0;

            if (totalDays > 30) {
                overtimePay = extraDays * perDaySalary;
            } else if (totalDays < 30) {
                deductions = extraDays * perDaySalary;
            }

            double netSalary = basicSalary + overtimePay - deductions;

            stat = con.prepareStatement("INSERT INTO Payroll (EmployeeID,payrollId, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?,?, ?, ?, ?, ?, ?)");
            stat.setInt(1, employeeId);
            stat.setInt(2, payrollId);
            stat.setDate(3, java.sql.Date.valueOf(startDate));
            stat.setDate(4, java.sql.Date.valueOf(endDate));
            stat.setDouble(5, basicSalary);
            stat.setDouble(6, overtimePay);
            stat.setDouble(7, deductions);
            stat.setDouble(8, netSalary);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new PayrollGenerationException("Error generating payroll: " + ex.getMessage());
        }
    }
    public void GetPayrollById(int payrollId) throws PayrollGenerationException {
        try {
            stat = con.prepareStatement("SELECT * FROM Payroll WHERE PayrollID = ?");
            stat.setInt(1, payrollId);
            ResultSet resultSet = stat.executeQuery();
            
                
                int employeeID = resultSet.getInt("EmployeeID");
                LocalDate payPeriodStartDate = resultSet.getDate("PayPeriodStartDate").toLocalDate();
                LocalDate payPeriodEndDate = resultSet.getDate("PayPeriodEndDate").toLocalDate();
                double basicSalary = resultSet.getDouble("BasicSalary");
                double overtimePay = resultSet.getDouble("OvertimePay");
                double deductions = resultSet.getDouble("Deductions");
                double netSalary = resultSet.getDouble("NetSalary");

                System.out.println("EmployeeID: " + employeeID);
                System.out.println("PayPeriodStartDate: " + payPeriodStartDate);
                System.out.println("PayPeriodEndDate: " + payPeriodEndDate);
                System.out.println("BasicSalary: " + basicSalary);
                System.out.println("OvertimePay: " + overtimePay);
                System.out.println("Deductions: " + deductions);
                System.out.println("NetSalary: " + netSalary);
            
        } catch (SQLException ex) {
            throw new PayrollGenerationException("Error getting payroll by ID: " + ex.getMessage());
        }
    }

  
    public void GetPayrollsForEmployee(int employeeId) throws PayrollGenerationException {
        try {
            stat = con.prepareStatement("SELECT * FROM Payroll WHERE EmployeeID = ?");
            stat.setInt(1, employeeId);
            ResultSet resultSet = stat.executeQuery();
            
            while(resultSet.next()) {
                
                int payrollID = resultSet.getInt("PayrollID");
                LocalDate payPeriodStartDate = resultSet.getDate("PayPeriodStartDate").toLocalDate();
                LocalDate payPeriodEndDate = resultSet.getDate("PayPeriodEndDate").toLocalDate();
                double basicSalary = resultSet.getDouble("BasicSalary");
                double overtimePay = resultSet.getDouble("OvertimePay");
                double deductions = resultSet.getDouble("Deductions");
                double netSalary = resultSet.getDouble("NetSalary");

                System.out.println("PayrollID: " + payrollID);
                System.out.println("EmployeeID: " + employeeId);
                System.out.println("PayPeriodStartDate: " + payPeriodStartDate);
                System.out.println("PayPeriodEndDate: " + payPeriodEndDate);
                System.out.println("BasicSalary: " + basicSalary);
                System.out.println("OvertimePay: " + overtimePay);
                System.out.println("Deductions: " + deductions);
                System.out.println("NetSalary: " + netSalary);
               
                
            }
           
        } catch (SQLException ex) {
            throw new PayrollGenerationException("Error getting payrolls for employee: " + ex.getMessage());
        }
    }

   
   public void GetPayrollsForPeriod(LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
    PreparedStatement stat = null;
    ResultSet resultSet = null;
    try {
        stat = con.prepareStatement("SELECT * FROM Payroll WHERE PayPeriodStartDate = ? AND PayPeriodEndDate = ?");
        
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        
        stat.setDate(1, sqlStartDate);
        stat.setDate(2, sqlEndDate);
          
        resultSet = stat.executeQuery();

        while(resultSet.next()) {
            int payrollID = resultSet.getInt("PayrollID");
            int employeeID = resultSet.getInt("EmployeeID");
            double basicSalary = resultSet.getDouble("BasicSalary");
            double overtimePay = resultSet.getDouble("OvertimePay");
            double deductions = resultSet.getDouble("Deductions");
            double netSalary = resultSet.getDouble("NetSalary");

            System.out.println("PayrollID: " + payrollID);
            System.out.println("EmployeeID: " + employeeID);
            System.out.println("PayPeriodStartDate: " + startDate);
            System.out.println("PayPeriodEndDate: " + endDate);
            System.out.println("BasicSalary: " + basicSalary);
            System.out.println("OvertimePay: " + overtimePay);
            System.out.println("Deductions: " + deductions);
            System.out.println("NetSalary: " + netSalary);             
        }
    } catch (SQLException ex) {
        throw new PayrollGenerationException("Error getting payrolls for the period: " + ex.getMessage());
    }
}

    
    }

    

