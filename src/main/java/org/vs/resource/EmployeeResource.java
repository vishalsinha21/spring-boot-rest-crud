package org.vs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vs.domain.EmployeeView;
import org.vs.domain.ErrorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeResource {

    public static Map<Integer, EmployeeView> employees;

    @Autowired
    public EmployeeResource() {
        EmployeeView emp1 = new EmployeeView(1, "Vishal");
        EmployeeView emp2 = new EmployeeView(2, "Rajat");
        EmployeeView emp3 = new EmployeeView(3, "Nishant");

        employees = new HashMap<>();
        employees.put(1, emp1);
        employees.put(2, emp2);
        employees.put(3, emp3);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.ok(new ArrayList<>(employees.values()));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity getEmployee(@PathVariable("id") Integer empId) {
        return employees.containsKey(empId) ?
                ResponseEntity.ok(employees.get(empId)) :
                ResponseEntity.badRequest().body(new ErrorView("NOT_FOUND", "No employee found for id"));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable("id") Integer empId) {
        if (employees.containsKey(empId)) {
            EmployeeView employeeView = employees.get(empId);
            employees.remove(empId);
            return ResponseEntity.ok(employeeView);
        } else {
            return ResponseEntity.badRequest().body(new ErrorView("NOT_FOUND", "No employee found for id"));
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@PathVariable("id") Integer empId, @RequestBody EmployeeView employee) {
        if (employees.containsKey(empId)) {
            return ResponseEntity.badRequest().body(new ErrorView("ALREADY_EXIST", "Employee with id already exist"));
        }

        employees.put(empId, employee);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@PathVariable("id") Integer empId, @RequestBody EmployeeView employee) {
        if (!employees.containsKey(empId)) {
            return ResponseEntity.badRequest().body(new ErrorView("NOT_FOUND", "No employee found for id"));
        }

        employees.put(empId, employee);
        return ResponseEntity.ok(employee);
    }

}
