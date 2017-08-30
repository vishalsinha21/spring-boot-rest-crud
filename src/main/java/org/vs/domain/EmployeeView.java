package org.vs.domain;

import java.math.BigInteger;

public class EmployeeView {

    private Integer employeeId;
    private String name;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeView() {
    }

    public EmployeeView(Integer employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }
}
