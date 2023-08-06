package com.epam.rd.tasks.sqlqueries;

/**
 * Implement sql queries like described
 */
public class SqlQueries {
    //Select all employees sorted by last name in ascending order
    //language=HSQLDB
    String select01 = "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
            " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
            " FROM EMPLOYEE" +
            " ORDER BY LASTNAME ASC";

    //Select employees having no more than 5 characters in last name sorted by last name in ascending order
    //language=HSQLDB
    String select02 = "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
            " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
            " FROM EMPLOYEE" +
            " WHERE LENGTH(LASTNAME) < 6" +
            " ORDER BY LASTNAME ASC";

    //Select employees having salary no less than 2000 and no more than 3000
    //language=HSQLDB
    String select03 = "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
            " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
            " FROM EMPLOYEE" +
            " WHERE SALARY >= 2000 AND SALARY <= 3000";

    //Select employees having salary no more than 2000 or no less than 3000
    //language=HSQLDB
    String select04 = "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
            " POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT" +
            " FROM EMPLOYEE" +
            " WHERE SALARY <= 2000 OR SALARY >= 3000";

    //Select all employees assigned to departments and corresponding department
    //language=HSQLDB
    String select05 = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.MIDDLENAME," +
                    " E.POSITION, E.MANAGER, E.HIREDATE, E.SALARY, E.DEPARTMENT" +
                    " D.ID, D.NAME, D.LOCATION" +
                    " FROM EMPLOYEE AS E " +
                    " INNER JOIN DEPARTMENT AS D ON E.DEPARTMENT = D.ID";

    //Select all employees and corresponding department name if there is one.
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select06 = "SELECT ID, FIRSTNAME, LASTNAME, MIDDLENAME," +
            " POSITION, MANAGER, HIREDATE, SALARY, NAME " +
            " DEPNAME" +
            " FROM EMPLOYEE FULL JOIN DEPARTMENT " +
            " ON EMPLOYEE.DEPARTMENT = DEPARTMENT.ID WHERE EMPLOYEE.ID IS NOT NULL";

    //Select total salary pf all employees. Name it "total".
    //language=HSQLDB
    String select07 = "SELECT SUM(SALARY) AS total" +
            " FROM EMPLOYEE";

    //Select all departments and amount of employees assigned per department
    //Name column containing name of the department "depname".
    //Name column containing employee amount "staff_size".
    //language=HSQLDB
    String select08 = "SELECT ID, NAME DEPNAME, LOCATION, " +
            " COUNT(E.ID) AS staff_size " +
            " FROM DEPARTMENT AS D " +
            " INNER JOIN EMPLOYEE AS E ON D.ID = E.DEPARTMENT" +
            " GROUP BY D.ID";


    //Select all departments and values of total and average salary per department
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select09 = "SELECT ID, NAME DEPNAME, LOCATION, " +
            " SUM(E.SALARY) AS total, AVG(E.SALARY) AS average" +
            " FROM DEPARTMENT AS D " +
            " INNER JOIN EMPLOYEE AS E ON D.ID = E.DEPARTMENT" +
            " GROUP BY D.ID";

    //Select lastnames of all employees and lastnames of their managers if an employee has a manager.
    //Name column containing employee's lastname "employee".
    //Name column containing manager's lastname "manager".
    //language=HSQLDB
    String select10 = "SELECT E.LASTNAME employee, M.LASTNAME manager" +
            " FROM EMPLOYEE AS E FULL JOIN EMPLOYEE AS M ON E.MANAGER = M.ID " +
            " WHERE E.ID IS NOT NULL";
}
