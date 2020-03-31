import java.sql.SQLException;
import java.util.Date;
import java.util.TreeMap;
import java.util.Map;

public class Employee implements Comparable<Employee> {
    private String id;
    private String firstName;
    private String lastName;
    private String departmentId;
    private Date hireDate;

    public Employee() {
        // empty
    }

    public Employee(String id, String firstName, String lastName, String departmentId, Date hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.hireDate = hireDate;
    }

    @Override
    public int compareTo(Employee other) {
        return hireDate.compareTo(other.hireDate);
    }

    public static Map<String, Employee> getAllEmployees(DatabaseConnectionManager jdbc) {
        Map<String, Employee> employeeMap = new TreeMap<>();

        try {
            jdbc.select("employees");
            while(jdbc.getResultSet().next()) {
                Employee employee = new Employee();

                employee.setId(jdbc.getResultSet().getString(1));
                employee.setFirstName(jdbc.getResultSet().getString(2));
                employee.setLastName(jdbc.getResultSet().getString(3));
                employee.setHireDate(jdbc.getResultSet().getDate(4));
                employee.setDepartmentId(jdbc.getResultSet().getString(5));

                employeeMap.put(employee.getId(), employee);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return employeeMap;

    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }


}
