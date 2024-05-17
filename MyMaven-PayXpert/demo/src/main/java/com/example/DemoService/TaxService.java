package com.example.DemoService;

import com.example.DemoDao.*;
import com.example.Exception.*;

import java.util.Scanner;

public class TaxService {
    private final Scanner scanner;
    private final TaxDao taxDao;

    public TaxService() {
        scanner = new Scanner(System.in);
        try {
            taxDao = new TaxDao();
        } catch (DatabaseConnectionException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Could not connect to database.");
        }
    }

    public void calculateTax() {
        try {
            System.out.println("Enter Employee ID:");
            int employeeId = scanner.nextInt();
            System.out.println("Enter tax ID:");
            int taxId = scanner.nextInt();
           
            System.out.println("Enter Taxable Income:");
            double taxableIncome = scanner.nextDouble();
            System.out.println("Enter Tax Year:");
            int taxYear = scanner.nextInt();

            taxDao.CalculateTax(employeeId, taxId, taxableIncome, taxYear);
            System.out.println("Tax calculated successfully.");
        } catch (TaxCalculationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getTaxById() {
        try {
            System.out.println("Enter Tax ID:");
            int taxId = scanner.nextInt();
            taxDao.GetTaxById(taxId);
        } catch (TaxCalculationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getTaxesForEmployee() {
        try {
            System.out.println("Enter Employee ID:");
            int employeeId = scanner.nextInt();
            taxDao.GetTaxesForEmployee(employeeId);
        } catch (TaxCalculationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getTaxesForYear() {
        try {
            System.out.println("Enter Tax Year:");
            int taxYear = scanner.nextInt();
            taxDao.GetTaxesForYear(taxYear);
        } catch (TaxCalculationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
