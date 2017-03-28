/**
 * Created by Tuly on 3/28/2017.
 */
public class Department {

    private final String name, phone;
    private Employee director;
    private City city;

    public Department(String name, String phone, City city) {
        this.name = name;
        this.phone = phone;
        this.city = city;
    }

    public Department(String name, String phone, Employee director, City city) {
        this.name = name;
        this.phone = phone;
        this.director = director;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public City getCity() throws NullFieldException {
        if (city == null)
            throw new NullFieldException("You must specify a city for the department");
        return city;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", director=" + (director == null ? "non presente" : director) +
                ", city=" + city +
                '}';
    }
}
