package com.example.DemoDao;



import java.time.LocalDate;

import com.example.Exception.*;

public abstract class IFinancialRecordService {
    

   
    public abstract void AddFinancialRecord(int employeeId,int recordId, String description, double amount, String recordType) throws FinancialRecordException;

    public abstract void GetFinancialRecordById(int recordId) throws FinancialRecordException;

    public abstract void GetFinancialRecordsForEmployee(int employeeId) throws FinancialRecordException;

    public abstract void GetFinancialRecordsForDate(LocalDate recordDate) throws FinancialRecordException;
}
