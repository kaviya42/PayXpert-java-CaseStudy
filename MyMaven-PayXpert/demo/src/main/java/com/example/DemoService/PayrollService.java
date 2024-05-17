package com.example.DemoService;

import com.example.DemoDao.*;
import com.example.Exception.*;

import java.time.LocalDate;
import java.util.Scanner;

public class PayrollService {
    private final Scanner scanner;
    private final PayrollDao payrollDao;

    public PayrollService() {
        scanner = new Scanner(System.in);
        try {
            payrollDao = new PayrollDao();
        } catch (DatabaseConnectionException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Could not connect to database.");
        }
    }

    public void generatePayroll() {
        try {
            System.out.println("Enter Employee ID:");
            int employeeId = scanner.nextInt();
            System.out.println("Enter payrollID ID:");
            int payrollId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Pay Period Start Date (YYYY-MM-DD):");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Pay Period End Date (YYYY-MM-DD):");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            payrollDao.GeneratePayroll(employeeId,payrollId, startDate, endDate);
            System.out.println("Payroll generated successfully.");
        } catch (PayrollGenerationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPayrollById() {
        try {
            System.out.println("Enter Payroll ID:");
            int payrollId = scanner.nextInt();
            payrollDao.GetPayrollById(payrollId);
        } catch (PayrollGenerationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPayrollsForEmployee() {
        try {
            System.out.println("Enter Employee ID:");
            int employeeId = scanner.nextInt();
            payrollDao.GetPayrollsForEmployee(employeeId);
        } catch (PayrollGenerationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPayrollsForPeriod() {
        try {
            System.out.println("Enter Pay Period Start Date (YYYY-MM-DD):");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Pay Period End Date (YYYY-MM-DD):");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            payrollDao.GetPayrollsForPeriod(startDate, endDate);
        } catch (PayrollGenerationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
