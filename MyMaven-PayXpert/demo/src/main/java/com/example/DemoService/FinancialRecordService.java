package com.example.DemoService;

import java.time.LocalDate;
import java.util.Scanner;



import com.example.DemoDao.*;
import com.example.Exception.*;

public class FinancialRecordService {
    Scanner sc;
    FinancialRecordDao frDao;

    public FinancialRecordService() {
        sc = new Scanner(System.in);
        try {
            frDao = new FinancialRecordDao();
        } catch (DatabaseConnectionException ex) {
            System.out.println(ex.getMessage());
           
        }
    }

    public void addFinancialRecord() {
        try {
            System.out.println("Enter Employee ID:");
            int employeeId = sc.nextInt();
            System.out.println("Enter record ID:");
            int recordId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Description:");
            String description = sc.nextLine();
            System.out.println("Enter Amount:");
            double amount = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter Record Type:");
            String recordType = sc.nextLine();

            frDao.AddFinancialRecord(employeeId,recordId, description, amount, recordType);
        } catch (FinancialRecordException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showFinancialRecordById() {
        System.out.println("Enter Record ID:");
        int recordId = sc.nextInt();
        try {
            frDao.GetFinancialRecordById(recordId);
        } catch (FinancialRecordException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showFinancialRecordsForEmployee() {
        System.out.println("Enter Employee ID:");
        int employeeId = sc.nextInt();
        try {
            frDao.GetFinancialRecordsForEmployee(employeeId);
        } catch (FinancialRecordException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showFinancialRecordsForDate() {
        System.out.println("Enter Record Date (YYYY-MM-DD):");
        LocalDate recordDate = LocalDate.parse(sc.nextLine());
        try {
            frDao.GetFinancialRecordsForDate(recordDate);
        } catch (FinancialRecordException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
