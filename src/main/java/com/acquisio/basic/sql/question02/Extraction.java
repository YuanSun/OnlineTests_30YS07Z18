package com.acquisio.basic.sql.question02;

import com.acquisio.util.dbutil.H2DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Write a query to extract all employees that are not assigned to any project.
 * -- The select should output those columns
 * -- |FirstName|LastName|DepartmentName|
 *
 * Here is the database schema to use.
 * <p>
 * employees                             projects
 * +---------------+---------+           +---------------+---------+
 * | id            | int     |<----+  +->| id            | int     |
 * | first_name    | varchar |     |  |  | title         | varchar |
 * | last_name     | varchar |     |  |  | start_date    | date    |
 * | salary        | int     |     |  |  | end_date      | date    |
 * | department_id | int     |--+  |  |  | budget        | int     |
 * +---------------+---------+  |  |  |  +---------------+---------+
 * |  |  |
 * departments                  |  |  |  employees_projects
 * +---------------+---------+  |  |  |  +---------------+---------+
 * | id            | int     |<-+  |  +--| project_id    | int     |
 * | name          | varchar |     +-----| employee_id   | int     |
 * +---------------+---------+           +---------------+---------+
 */
public class Extraction {
    public static void main(String[] args) throws SQLException {
        H2DBUtil.startDatabase();
        try {
            Extraction instance = new Extraction();
            instance.printNumberOfEmployeesPerProject();
        } finally {
            H2DBUtil.stopDatabase();
        }
    }

    private void printNumberOfEmployeesPerProject() throws SQLException {
        try (Connection conn = H2DBUtil.getConnection()) {
            // Start : For consultation only, can be removed
//            H2DBUtil.displayTableRows(conn, "projects");
//            H2DBUtil.displayTableRows(conn, "departments");
//            H2DBUtil.displayTableRows(conn, "employees");
//            H2DBUtil.displayTableRows(conn, "employees_projects");
            // End : For consultation only, can be removed

            // TODO: Insert query here
            // See requirement in this class javadoc
        	String query = "SELECT employees.first_name as FirstName, employees.last_name as LastName, departments.name as DepartmentName"
            		+ "	FROM employees INNER JOIN departments ON employees.department_id = departments.id"
            		+ " LEFT JOIN employees_projects ON employees.id = employees_projects.employee_id"
            		+ " WHERE employees_projects.project_id IS NULL";
            
            ResultSet resultSet = conn.createStatement().executeQuery(query);
            H2DBUtil.displayResultSet(resultSet);

        }
    }
}
