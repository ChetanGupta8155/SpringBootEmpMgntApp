package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j

public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
  public ResponseEntity<String>signUp(@RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);

        return ResponseEntity.ok("SIGNUP DONE SUCCESSFULLY");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean>signIn(@PathVariable String empEmailId, String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee>getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }


    @GetMapping("/getdatabyname/{empName}")
public  ResponseEntity<List<Employee>>getDataByName(@PathVariable String empName){
        return  ResponseEntity.ok(employeeServiceImpl.getDataByName(empName));
    }

    @GetMapping("/sortbysalary")
public ResponseEntity<List<Employee>> sortBySalary(){
        return  ResponseEntity.ok(employeeServiceImpl.sortBySalary());
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
public ResponseEntity<Employee>getDataByContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
public ResponseEntity<Employee>getDataByEmailId(@PathVariable String empEmailId){
        return  ResponseEntity.ok(employeeServiceImpl.getDataByEmailId(empEmailId));
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>>filterDataBySalary(@PathVariable double empSalary){
        return  ResponseEntity.ok(employeeServiceImpl.filterDataBySalary(empSalary));
    }


@GetMapping("/sortbyname")
public ResponseEntity<List<Employee>>sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.sortByName());
}

@GetMapping("/getdatabydOB/{empDOB}")
public ResponseEntity<Employee>getDataByDOB(@PathVariable Date empDOB){
        return  ResponseEntity.ok(employeeServiceImpl.getDataByDOB(empDOB));
}

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }


@PutMapping("/updatedata/{empId}")

public ResponseEntity<String>updateData(@PathVariable int empId, @RequestBody Employee employee){

        if(employeeServiceImpl.getDataById(empId).getEmpId()==empId) {
            employeeServiceImpl.updateData(empId, employee);
        }else{
            throw  new RecordNotFoundException("Employee Id does not Exist");
        }
        return ResponseEntity.ok("DATA UPDATED SUCCESSFULLY");
}


    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data deleted successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String>deleteAllData(){
        employeeServiceImpl.getAllData();
        return ResponseEntity.ok("DELETED ALL DATA");
    }

}
