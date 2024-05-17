package com.example.DemoDao;
import com.example.model.*;
import com.example.Exception.*;

public abstract class IEmployeeService {
  
    public abstract void selectEmployee(int employeeID) throws EmployeeNotFoundException;
    public abstract void add(Employee e) throws InvalidInputException;

    public abstract void update(String position, int employeeID) throws EmployeeNotFoundException;

    public abstract void remove(int employeeID) throws EmployeeNotFoundException;

    public abstract void show() throws EmployeeNotFoundException;
}
