package com.csi.dao;

import com.csi.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public Employee getDataById(int empId);

    public List<Employee>getDataByName(String empName);

    public List<Employee> sortBySalary();

    public Employee getDataByContactNumber(long empContactNumber);

    public Employee getDataByEmailId(String empEmailId);

    public List<Employee> filterDataBySalary(double empSalary);

    public List<Employee> sortByName();

    public Employee getDataByDOB(Date empDOB);

    public List<Employee> getAllData();

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();
}
