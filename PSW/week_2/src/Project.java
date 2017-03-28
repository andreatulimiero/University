/**
 * Created by Tuly on 3/28/2017.
 */
public class Project {

    private final String name;
    private final double budget;
    private City city;

    public Project(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public Project(String name, double budget, City city) {
        this.name = name;
        this.budget = budget;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                ", city=" + city +
                '}';
    }
}
