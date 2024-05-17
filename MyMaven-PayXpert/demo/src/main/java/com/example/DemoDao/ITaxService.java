package com.example.DemoDao;




import com.example.Exception.*;

public abstract class ITaxService {


   
public abstract void CalculateTax(int employeeId,int taxId,double taxableIncome,int taxYear)throws TaxCalculationException;
public abstract void GetTaxById(int taxId)throws TaxCalculationException;
public abstract void GetTaxesForEmployee(int employeeId)throws TaxCalculationException;
public abstract void GetTaxesForYear(int taxYear)throws TaxCalculationException;
}
