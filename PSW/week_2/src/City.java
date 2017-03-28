/**
 * Created by Tuly on 3/28/2017.
 */
public class City {

    private final String name, region;

    public City(String name, String region) {
        this.name = name;
        this.region = region;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
