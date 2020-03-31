// Mads Christensen DAT19b

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        DatabaseConnectionManager jdbc = new DatabaseConnectionManager();
        Map<String, Employee> employeeMap = null;

        // Printer employees tabellen
        try {
            jdbc.openConnection();
            jdbc.select("employees");

            while (jdbc.getResultSet().next()) {
                for (int i = 1; i <= jdbc.getResultSet().getMetaData().getColumnCount(); i++) {
                    System.out.print(jdbc.getResultSet().getMetaData().getColumnName(i) + ": ");
                    System.out.println(jdbc.getResultSet().getString(i));
                }
                System.out.println();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        // Min måde at sortere mappet
        try {
            jdbc.openConnection();
            employeeMap = Employee.getAllEmployees(jdbc);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(employeeMap != null) {
            Collection<Employee> employees = employeeMap.values();
            List<Employee> employeeList = new ArrayList<>(employees);

            Collections.sort(employeeList);

            System.out.println(employeeList);
        }

        // Alternativ måde at sortere mappet via Comparator
        System.out.println(TreeMapComparator.sortByValues(employeeMap));

        try {
            jdbc.closeConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
