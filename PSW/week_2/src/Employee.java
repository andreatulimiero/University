import java.util.HashSet;

/**
 * Created by Tuly on 3/28/2017.
 */
public class Employee {

    private final String name, surname;
    private HashSet<Department> departments;
    private HashSet<Project> projects;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;

        departments = new HashSet<>();
        projects = new HashSet<>();
    }

    public void addDepartment(Department department) {
        if (department == null) return;
        departments.add(department);

    }

    public void removeDepartment(Department department) {
        if (department == null) return;
        departments.remove(department);
    }

    public void addProject(Project project){
        if (project == null) return;
        projects.add(project);
    }

    public void removeProject(Project project) {
        if (project == null) return;
        projects.remove(project);
    }

    public void setDepartments(HashSet<Department> departments) {
        this.departments = departments;
    }

    public void setProjects(HashSet<Project> projects) {
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public HashSet<Department> getDepartments() {
        return departments;
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
