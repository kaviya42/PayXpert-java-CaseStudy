package com.example.Client;
//import Connect.DataConnect;
import java.util.*;

import com.example.DemoService.*;
public class Main {
        static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        EmployeeService employeeService= new EmployeeService();
         FinancialRecordService financialRecordService=new FinancialRecordService();
         PayrollService payrollService=new PayrollService();
         TaxService taxService=new TaxService();
         while (true) {
            System.out.println("1 Employee");
            System.out.println("2 FinancialRecords management");
            System.out.println("3 Payroll Management");
            System.out.println("4 Tax Management");
            System.out.println("Enter choice");
            int ch = sc.nextInt();
            if(ch==1){
                while (true) {
                    System.out.println("1 Get Employee By Id");
                    System.out.println("2 Get All Employees");
                    System.out.println("3 Add Employee");
                    System.out.println("4 Update Employee");
                    System.out.println("5 Remove Employee");
                    System.out.println("Enter choice");
                    int Empch = sc.nextInt();
                    if(Empch==1){
                        employeeService.ShowEmployeeById();
                    }else if(Empch==2){
                        employeeService.displayEmployees();
                    }else if(Empch==3){
                        employeeService.addEmployee();
                    }else if(Empch==4){
                        employeeService.updateEmployeePosition();
                    }else if(Empch==5){
                        employeeService.removeEmployee();
                    }else break;
            }}
            else if(ch==2){
                while (true) {
                    System.out.println("1 Add Financial Record");
                    System.out.println("2 Get Financial Record By Id");
                    System.out.println("3 Get FinancialRecords For Employee");
                    System.out.println("4 Get FinancialRecords For Date");
                    System.out.println("Enter choice");
                    int FRch = sc.nextInt();
                    if(FRch==1){
                        financialRecordService.addFinancialRecord();
                    }else if(FRch==2){
                        financialRecordService.showFinancialRecordById();
                    }else if(FRch==3){
                        financialRecordService.showFinancialRecordsForEmployee();
                    }else if(FRch==4){
                        financialRecordService.showFinancialRecordsForDate();
                    } else break; }}

                    else if(ch==3){
                        while (true) {
                            System.out.println("1 Generate Payroll");
                            System.out.println("2 Get Payroll By Id");
                            System.out.println("3 Get Payrolls For Employee");
                            System.out.println("4 Get Payrolls For Period");
                            System.out.println("Enter choice");
                            int Pch = sc.nextInt();
                            if(Pch==1){
                                payrollService.generatePayroll();
                            }else if(Pch==2){
                               payrollService.getPayrollById();
                            }else if(Pch==3){
                               payrollService.getPayrollsForEmployee();
                            }else if(Pch==4){
                                payrollService.getPayrollsForPeriod();
                            } else break; }}

                            else if(ch==4){
                                while (true) {
                                    System.out.println("1 Calculate Tax");
                                    System.out.println("2 Get Tax By Id");
                                    System.out.println("3 Get Taxes For Employee");
                                    System.out.println("4 Get Taxes For Year");
                                    System.out.println("Enter choice");
                                    int Tch = sc.nextInt();
                                    if(Tch==1){
                                        taxService.calculateTax();
                                    }else if(Tch==2){
                                       taxService.getTaxById();;
                                    }else if(Tch==3){
                                       taxService.getTaxesForEmployee();
                                    }else if(Tch==4){
                                       taxService.getTaxesForYear();
                                    } else break; }}
                                    else 
                                   break;
            }
           
         }
}
