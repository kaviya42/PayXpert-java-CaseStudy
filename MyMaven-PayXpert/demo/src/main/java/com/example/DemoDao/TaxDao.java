package com.example.DemoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.Connect.*;
import com.example.Exception.*;



public class TaxDao extends ITaxService {
 private Connection con;
    private PreparedStatement stat;
    public TaxDao() throws DatabaseConnectionException {
        con = DataConnect.getConnect();
    }

   
    public void CalculateTax(int employeeId,int taxId,double taxableIncome, int taxYear) throws TaxCalculationException {
        double taxAmount;
        if(taxableIncome<=20000){
            taxAmount=taxableIncome*0.1;
            
        }else{
            taxAmount=taxableIncome*0.2;
           
        }
        try{
        stat = con.prepareStatement("INSERT INTO tax (employeeId,taxId,taxableIncome,taxamount,taxYear)values(?,?,?,?,?)");
         stat.setInt(1,employeeId);
         stat.setInt(2,taxId);
         stat.setDouble(3,taxableIncome);
         stat.setDouble(4,taxAmount);
         stat.setInt(5,taxYear);
         stat.executeUpdate();
     }catch (SQLException ex) {
        throw new TaxCalculationException("Error calculating tax " + ex.getMessage());
    }
    }

   
    public void GetTaxById(int taxId) throws TaxCalculationException {
        try {
            stat = con.prepareStatement("SELECT * FROM Tax WHERE TaxID = ?");
            stat.setInt(1, taxId);
            ResultSet resultSet = stat.executeQuery();
          if(resultSet.next()){
                int employeeID = resultSet.getInt("EmployeeID");
                int taxID = resultSet.getInt("TaxID");
                double taxableIncome = resultSet.getDouble("TaxableIncome");
                double taxAmount = resultSet.getDouble("TaxAmount");
    
                System.out.println("EmployeeID: " + employeeID);
                System.out.println("TaxID: " + taxID);
                System.out.println("TaxableIncome: " + taxableIncome);
                System.out.println("TaxAmount: " + taxAmount);}
            }  catch (SQLException ex) {
            throw new TaxCalculationException("Error getting tax by ID: " + ex.getMessage());
        }
    }
    


    public void GetTaxesForEmployee(int employeeId) throws TaxCalculationException {
        try {
            stat = con.prepareStatement("SELECT * FROM Tax WHERE EmployeeID = ?");
            stat.setInt(1, employeeId);
            ResultSet resultSet = stat.executeQuery();
           if(resultSet.next()){
                int taxID = resultSet.getInt("TaxID");
                double taxableIncome = resultSet.getDouble("TaxableIncome");
                double taxAmount = resultSet.getDouble("TaxAmount");
    
                System.out.println("EmployeeID: " + employeeId);
                System.out.println("TaxID: " + taxID);
                System.out.println("TaxableIncome: " + taxableIncome);
                System.out.println("TaxAmount: " + taxAmount);}
           
        } catch (SQLException ex) {
            throw new TaxCalculationException("Error getting tax information for employee ID: " + ex.getMessage());
        }
    }
    

   
    public void GetTaxesForYear(int taxYear) throws TaxCalculationException {
        try {
            stat = con.prepareStatement("SELECT * FROM Tax WHERE TaxYear = ?");
            stat.setInt(1, taxYear);
            ResultSet resultSet = stat.executeQuery();
            if(resultSet.next()){
                int employeeID = resultSet.getInt("EmployeeID");
                int taxID = resultSet.getInt("TaxID");
                double taxableIncome = resultSet.getDouble("TaxableIncome");
                double taxAmount = resultSet.getDouble("TaxAmount");
    
                System.out.println("EmployeeID: " + employeeID);
                System.out.println("TaxID: " + taxID);
                System.out.println("Income: " + taxableIncome);
                System.out.println("TaxAmount: " + taxAmount);}
            
        } catch (SQLException ex) {
            throw new TaxCalculationException("Error getting tax by year: " + ex.getMessage());
        }
    }
    
}