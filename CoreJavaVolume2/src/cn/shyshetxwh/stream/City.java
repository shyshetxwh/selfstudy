package cn.shyshetxwh.stream;

public class City {
    private String name;
    private String state;
    private int population;

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public City(String name, String state, int population) {

        this.name = name;
        this.state = state;
        this.population = population;
    }
}
