package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl implements  EmployeeDao{

private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
    @Override
    public void signUp(Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        Session session = factory.openSession();
        boolean flag = false;

        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {

                flag = true;
            }
        }
        return flag;

    }
    @Override
    public Employee getDataById(int empId) {
        return getAllData().stream().filter(emp->emp.getEmpId()==empId).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> getDataByName(String empName) {
        return getAllData().stream().filter(emp-> emp.getEmpName().equals(empName)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortBySalary() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).collect(Collectors.toList());
    }

    @Override
    public Employee getDataByContactNumber(long empContactNumber) {
        return getAllData().stream().filter(emp->emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()).get(0);
    }

    @Override
    public Employee getDataByEmailId(String empEmailId) {
        return getAllData().stream().filter(emp-> emp.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return getAllData().stream().filter(emp-> emp.getEmpSalary()==empSalary).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByName() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList());
    }

    @Override
    public Employee getDataByDOB(Date empDOB) {
        return getAllData().stream().filter(emp-> emp.getEmpDOB()==empDOB).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> getAllData() {

        Session session=factory.openSession();

        return session.createQuery("from Employee").list();
    }

    @Override
    public void updateData(int empId, Employee employee) {

        Session session=factory.openSession();

        Transaction transaction = session.beginTransaction();

        for(Employee employee1:getAllData()){
            if(employee1.getEmpId()==empId){
                employee1.setEmpPassword(employee.getEmpPassword());
                employee1.setEmpGender(employee.getEmpGender());
                employee1.setEmpEmailId(employee.getEmpEmailId());
                employee1.setEmpName(employee.getEmpName());
                employee1.setEmpDOB(employee.getEmpDOB());
                employee1.setEmpAddress(employee.getEmpAddress());
                employee1.setEmpSalary(employee.getEmpSalary());
                employee1.setEmpContactNumber(employee.getEmpContactNumber());

          session.save(employee1);
          transaction.commit();
            }
        }

    }

    @Override
    public void deleteDataById(int empId) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        for (Employee employee : getAllData()) {
            if (employee.getEmpId() == empId) {


                session.delete(employee);
                transaction.commit();
            }
        }
    }

            @Override
            public void deleteAllData() {
                Session session = factory.openSession();

                for (Employee employee2 : getAllData()) {
                        Transaction transaction = session.beginTransaction();

                        session.delete(employee2);
                        transaction.commit();
                    }
                }
            }

