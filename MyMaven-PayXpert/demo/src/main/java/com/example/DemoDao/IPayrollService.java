package com.example.DemoDao;



import java.time.LocalDate;


import com.example.Exception.*;

public abstract class IPayrollService {
  

   

    public abstract void GeneratePayroll(int employeeId,int payrollId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException;

    public abstract void GetPayrollById(int payrollId) throws PayrollGenerationException;

    public abstract void GetPayrollsForEmployee(int employeeId) throws PayrollGenerationException;

    public abstract void GetPayrollsForPeriod(LocalDate startDate, LocalDate endDate) throws PayrollGenerationException;
}
