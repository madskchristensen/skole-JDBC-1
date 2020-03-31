import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Department {
    private String id;
    private String name;

    public Department() {
        // empty
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Department> getSetOfDepartments(DatabaseConnectionManager jdbc) {
        Set<Department> departmentSet = new HashSet<>();

        try {
            jdbc.select("departments");
            while(jdbc.getResultSet().next()) {
                Department department = new Department();
                department.setId(jdbc.getResultSet().getString(1));
                department.setName(jdbc.getResultSet().getString(2));

                departmentSet.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
