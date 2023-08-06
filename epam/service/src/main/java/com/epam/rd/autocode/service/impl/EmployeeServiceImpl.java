package com.epam.rd.autocode.service.impl;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import com.epam.rd.autocode.service.EmployeeService;
import com.epam.rd.autocode.service.Paging;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class EmployeeServiceImpl implements EmployeeService {


    private static final String SQL_SELECT_DEPARTMENT_BY_ID =
            "SELECT NAME, LOCATION FROM DEPARTMENT WHERE ID=?";

    private static final String SQL_SELECT_MANAGER_ID_BY_EMPLOYEE_ID =
            "SELECT MANAGER FROM EMPLOYEE WHERE ID=?";

    private static final String SQL_SELECT_DEPARTMENT_ID_BY_EMPLOYEE_ID =
            "SELECT DEPARTMENT FROM EMPLOYEE WHERE ID=?";

    private static final String SQL_SELECT_EMPLOYEE_BY_ID =
            "SELECT FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " WHERE ID=?";

    private static final String SQL_SELECT_ALL_EMPLOYEES_SORT_BY_HIREDATE =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " ORDER BY HIREDATE" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_ALL_EMPLOYEES_SORT_BY_LASTNAME =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " ORDER BY LASTNAME" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_ALL_EMPLOYEES_SORT_BY_SALARY =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " ORDER BY SALARY" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";


    private static final String SQL_SELECT_ALL_EMPLOYEES_SORT_DEPARTMENT_NAME_AND_LASTNAME =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " ORDER BY DEPARTMENT, LASTNAME" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_HIREDATE =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY" +
                    " FROM EMPLOYEE" +
                    " WHERE DEPARTMENT=?" +
                    " ORDER BY HIREDATE" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_SALARY =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY" +
                    " FROM EMPLOYEE" +
                    " WHERE DEPARTMENT=?" +
                    " ORDER BY SALARY" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_LASTNAME =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY" +
                    " FROM EMPLOYEE" +
                    " WHERE DEPARTMENT=?" +
                    " ORDER BY LASTNAME" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_HIREDATE =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " WHERE MANAGER=?" +
                    " ORDER BY HIREDATE" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_SALARY =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " WHERE MANAGER=?" +
                    " ORDER BY SALARY" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_LASTNAME =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, HIREDATE, SALARY, DEPARTMENT" +
                    " FROM EMPLOYEE" +
                    " WHERE MANAGER=?" +
                    " ORDER BY LASTNAME" +
                    " OFFSET ? ROWS" +
                    " FETCH NEXT ? ROWS ONLY";

    private static final String SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_SALARY_N =
            "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
                    " POSITION, MANAGER, HIREDATE, SALARY" +
                    " FROM EMPLOYEE" +
                    " WHERE DEPARTMENT=?" +
                    " ORDER BY SALARY DESC";


    @Override
    public List<Employee> getAllSortByHireDate(Paging paging) {
        return getAllSortByKey(paging, SQL_SELECT_ALL_EMPLOYEES_SORT_BY_HIREDATE);
    }

    @Override
    public List<Employee> getAllSortByLastname(Paging paging) {
        return getAllSortByKey(paging, SQL_SELECT_ALL_EMPLOYEES_SORT_BY_LASTNAME);
    }

    @Override
    public List<Employee> getAllSortBySalary(Paging paging) {
        return getAllSortByKey(paging, SQL_SELECT_ALL_EMPLOYEES_SORT_BY_SALARY);
    }

    @Override
    public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {
        return getAllSortByKey(paging, SQL_SELECT_ALL_EMPLOYEES_SORT_DEPARTMENT_NAME_AND_LASTNAME);
    }

    @Override
    public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {
        return getByDepartmentSortByKey(department, paging, SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_HIREDATE);
    }

    @Override
    public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {
        return getByDepartmentSortByKey(department, paging, SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_SALARY);
    }

    @Override
    public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {
        return getByDepartmentSortByKey(department, paging, SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_LASTNAME);
    }

    @Override
    public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {
        return getByManagerSortByKey(manager, paging, SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_LASTNAME);
    }

    @Override
    public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {
        return getByManagerSortByKey(manager, paging, SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_HIREDATE);
    }

    @Override
    public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {
        return getByManagerSortByKey(manager, paging, SQL_SELECT_EMPLOYEES_BY_MANAGER_SORT_BY_SALARY);
    }

    @Override
    public Employee getWithDepartmentAndFullManagerChain(Employee employee) {

        Stack<Employee> managerStack = getAllManagers(employee);

        while(managerStack.size() >= 2) {
            Employee manager = managerStack.pop();
            Employee employee1 = managerStack.pop();

            BigInteger departmentId = getDepartmentIdByEmployeeId(employee1.getId());
            Department department = null;
            if(departmentId != null) {
                department = getDepartmentById(departmentId);
            }

            Employee fullEmployee = new Employee(employee1.getId(),
                    employee1.getFullName(),employee1.getPosition(),
                    employee1.getHired(),employee1.getSalary(),manager,department);
            managerStack.push(fullEmployee);
        }

        //System.out.println("size: " + managerStack.size());
        //System.out.println("peek: " + managerStack.peek());

        return managerStack.pop();
    }

    @Override
    public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
        Map<Integer,Employee> employees = new HashMap<>();
        PreparedStatement statement = null;
        Connection connection = null;


        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEES_BY_DEPARTMENT_SORT_BY_SALARY_N);
            statement.setInt(1, department.getId().intValue());
            ResultSet resultSet = statement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                Employee   employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null,
                        null
                );
                if (resultSet.getInt("MANAGER") == 0 &&
                        department.getId().intValue() == 0) {
                    employees.put(i, employee);
                } else {
                    employees.put(i, getWithDepartmentAndManager(employee,
                            BigInteger.valueOf(resultSet.getInt("MANAGER")),
                            department.getId()));
                }
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employees.get(salaryRank);
    }


    private BigInteger getManagerIdByEmployeeId(BigInteger Id) {
        BigInteger managerId = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_MANAGER_ID_BY_EMPLOYEE_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                managerId = BigInteger.valueOf(resultSet.getInt("MANAGER"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return managerId;
    }

    private Department getDepartmentById(BigInteger Id) {
        Department department = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_BY_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                department = new Department(Id,
                        resultSet.getString("NAME"),
                        resultSet.getString("LOCATION"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return department;
    }

    private BigInteger getDepartmentIdByEmployeeId(BigInteger Id) {
        BigInteger departmentId = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_DEPARTMENT_ID_BY_EMPLOYEE_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                departmentId = BigInteger.valueOf(resultSet.getInt("DEPARTMENT"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return departmentId;
    }

    private Stack<Employee> getAllManagers(Employee employee) {
        Stack<Employee> managerStack = new Stack<>();
        BigInteger managerId = getManagerIdByEmployeeId(employee.getId());

        managerStack.push(employee);

        while (managerId != null) {
            if (managerId.intValue() == 0) break;

            Employee nextManager  = null;
            PreparedStatement statement = null;
            Connection connection = null;

            try {
                connection = ConnectionSource.instance().createConnection();
                statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
                statement.setInt(1, managerId.intValue());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    //System.out.println("333:" + managerId);
                    nextManager = new Employee(
                            managerId,
                            new FullName(resultSet.getString("FIRSTNAME"),
                                    resultSet.getString("LASTNAME"),
                                    resultSet.getString("MIDDLENAME")),
                            Position.valueOf(resultSet.getString("POSITION")),
                            resultSet.getDate("HIREDATE").toLocalDate(),
                            BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                            null,
                            null
                    );
                    managerStack.push(nextManager);
                }
            } catch (SQLException e) {
                System.err.println("SQL exception (request or table failed): " + e);
            } finally {
                close(statement);
                close(connection);
            }
            if (nextManager !=null) {
                managerId = getManagerIdByEmployeeId(nextManager.getId());
            } else {
                managerId = null;
            }
        }

        return managerStack;
    }

    private Employee getWithDepartmentAndManager(Employee employee, BigInteger managerId, BigInteger employeeDepartmentId) {
        Department employeeDepartment = null;
        if(employeeDepartmentId != null) {
            employeeDepartment = getDepartmentById(employeeDepartmentId);
        }

        return new Employee(employee.getId(),
                employee.getFullName(),employee.getPosition(),
                employee.getHired(),employee.getSalary(),getEmployeeWithDepartmentById(managerId),employeeDepartment);
    }

    private Employee getEmployeeWithDepartmentById(BigInteger Id) {
        Employee employee  = null;
        PreparedStatement statement = null;
        Connection connection = null;

        BigInteger employeeDepartmentId = getDepartmentIdByEmployeeId(Id);
        Department employeeDepartment = null;
        if(employeeDepartmentId != null) {
            employeeDepartment = getDepartmentById(employeeDepartmentId);
        }

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
            statement.setInt(1, Id.intValue());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                //System.out.println("333:" + Id);
                employee = new Employee(
                        Id,
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null,
                        employeeDepartment
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employee;
    }


    private List<Employee> getAllSortByKey(final Paging paging, final String sql) {

        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (paging.page-1)*paging.itemPerPage);
            statement.setInt(2, paging.itemPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee   employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null,
                        null
                );
                if (resultSet.getInt("MANAGER") == 0 &&
                        resultSet.getInt("DEPARTMENT") == 0) {
                    employees.add(employee);
                } else {
                    employees.add(getWithDepartmentAndManager(employee,
                            BigInteger.valueOf(resultSet.getInt("MANAGER")),
                            BigInteger.valueOf(resultSet.getInt("DEPARTMENT"))));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employees;
    }

    private List<Employee> getByDepartmentSortByKey(Department department, final Paging paging, final String sql) {

        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, department.getId().intValue());
            statement.setInt(2, (paging.page-1)*paging.itemPerPage);
            statement.setInt(3, paging.itemPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee   employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null,
                        null
                );
                if (resultSet.getInt("MANAGER") == 0 &&
                        department.getId().intValue() == 0) {
                    employees.add(employee);
                } else {
                    employees.add(getWithDepartmentAndManager(employee,
                            BigInteger.valueOf(resultSet.getInt("MANAGER")),
                            department.getId()));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employees;
    }

    private List<Employee> getByManagerSortByKey(Employee manager, final Paging paging, final String sql) {

        List<Employee> employees = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionSource.instance().createConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, manager.getId().intValue());
            statement.setInt(2, (paging.page-1)*paging.itemPerPage);
            statement.setInt(3, paging.itemPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee   employee = new Employee(
                        BigInteger.valueOf(resultSet.getInt("ID")),
                        new FullName(resultSet.getString("FIRSTNAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getString("MIDDLENAME")),
                        Position.valueOf(resultSet.getString("POSITION")),
                        resultSet.getDate("HIREDATE").toLocalDate(),
                        BigDecimal.valueOf(resultSet.getDouble("SALARY")),
                        null,
                        null
                );
                if (manager.getId().intValue() == 0 &&
                        resultSet.getInt("DEPARTMENT") == 0) {
                    employees.add(employee);
                } else {
                    employees.add(getWithDepartmentAndManager(employee,
                            manager.getId(),
                            BigInteger.valueOf(resultSet.getInt("DEPARTMENT"))));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            close(connection);
        }

        return employees;
    }

}
