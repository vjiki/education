package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.impl.RowMapperImpl;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return new RowMapperImpl();
    }
}
