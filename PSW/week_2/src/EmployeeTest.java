import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Tuly on 3/28/2017.
 */
public class EmployeeTest {

    HashSet<Department> departments;
    HashSet<Project> projects;
    Employee employee;

    @Before
    public void setUp() throws Exception {
        departments = new HashSet<>();
        projects = new HashSet<>();
        employee = new Employee("Ricciano", "Ronaldo");

        Department department = new Department("Sapienza", "000", new City("Rome", "Lazio"));
        departments.add(department);
        employee.addDepartment(department);

        Project project = new Project("Project KNOX", 230000);
        projects.add(project);
        employee.addProject(project);
    }

    @Test
    public void getDepartments() throws Exception {
        for (Department d: departments) {
            assertTrue(employee.getDepartments().contains(d));
        }
    }

    @Test
    public void getProjects() throws Exception {
        for (Project p: projects) {
            assertTrue(employee.getProjects().contains(p));
        }
    }

}