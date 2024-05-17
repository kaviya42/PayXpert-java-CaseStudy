package com.example.DemoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.Connect.*;
import com.example.Exception.*;

import java.sql.Date;

public class FinancialRecordDao extends IFinancialRecordService {
    private Connection con;
    private PreparedStatement stat;

    public FinancialRecordDao() throws DatabaseConnectionException {
        con = DataConnect.getConnect();
    }

    public void AddFinancialRecord(int employeeId,int recordId, String description, double amount, String recordType) throws FinancialRecordException {
        try {
            stat = con.prepareStatement("INSERT INTO FinancialRecord (EmployeeID,recordId,RecordDate, Description, Amount, RecordType) VALUES (?,?, ?, ?, ?, ?)");
            stat.setInt(1, employeeId);
            stat.setInt(2, recordId);
            stat.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stat.setString(4, description);
            stat.setDouble(5, amount);
            stat.setString(6, recordType);

            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new FinancialRecordException("Error adding financial record: " + ex.getMessage());
        }
    }

   
    public void GetFinancialRecordById(int recordId) throws FinancialRecordException {
        try {
            stat = con.prepareStatement("SELECT * FROM FinancialRecord WHERE RecordID = ?");
            stat.setInt(1, recordId);
            ResultSet resultSet = stat.executeQuery();
             if(resultSet.next()){
                int recordID = resultSet.getInt("RecordID");
                int employeeID = resultSet.getInt("EmployeeID");
                LocalDate recordDate = resultSet.getDate("RecordDate").toLocalDate();
                String description = resultSet.getString("Description");
                double amount = resultSet.getDouble("Amount");
                String recordType = resultSet.getString("RecordType");

                System.out.println("RecordID: " + recordID);
                System.out.println("EmployeeID: " + employeeID);
                System.out.println("RecordDate: " + recordDate);
                System.out.println("Description: " + description);
                System.out.println("Amount: " + amount);
                System.out.println("RecordType: " + recordType);}
              
           
        } catch (SQLException ex) {
            throw new FinancialRecordException("Error getting financial record by ID: " + ex.getMessage());
        }
    }

    
    public void GetFinancialRecordsForEmployee(int employeeId) throws FinancialRecordException {
        try {
            stat = con.prepareStatement("SELECT * FROM FinancialRecord WHERE EmployeeID = ?");
            stat.setInt(1, employeeId);
            ResultSet resultSet = stat.executeQuery();
           if(resultSet.next()){
                int recordID = resultSet.getInt("RecordID");
                LocalDate recordDate = resultSet.getDate("RecordDate").toLocalDate();
                String description = resultSet.getString("Description");
                double amount = resultSet.getDouble("Amount");
                String recordType = resultSet.getString("RecordType");

                System.out.println("RecordID: " + recordID);
                System.out.println("EmployeeID: " + employeeId);
                System.out.println("RecordDate: " + recordDate);
                System.out.println("Description: " + description);
                System.out.println("Amount: " + amount);
                System.out.println("RecordType: " + recordType);
               
           }
            }
           
         catch (SQLException ex) {
            throw new FinancialRecordException("Error getting financial records for employee: " + ex.getMessage());
        }
    }

    
    public void GetFinancialRecordsForDate(LocalDate recordDate) throws FinancialRecordException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM FinancialRecord WHERE RecordDate = ?");
            Date sqlDate = Date.valueOf(recordDate);
            stat.setDate(1, sqlDate);
    
            ResultSet resultSet = stat.executeQuery();
    
            while(resultSet.next()) {
                int recordID = resultSet.getInt("RecordID");
                int employeeID = resultSet.getInt("EmployeeID");
                String description = resultSet.getString("Description");
                double amount = resultSet.getDouble("Amount");
                String recordType = resultSet.getString("RecordType");
    
                System.out.println("RecordID: " + recordID);
                System.out.println("EmployeeID: " + employeeID);
                System.out.println("RecordDate: " + recordDate); 
                System.out.println("Description: " + description);
                System.out.println("Amount: " + amount);
                System.out.println("RecordType: " + recordType);
            } 
        } catch (SQLException ex) {
            throw new FinancialRecordException("Error getting financial records for date: " + ex.getMessage());
        }
    }
    
    
}
