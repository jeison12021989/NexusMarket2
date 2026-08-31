package application.domain.models;

public class Report implements Generable {

    private final String name;

    public Report(String name) {
        this.name = name;
    }

    @Override
    public boolean generate() {
        System.out.println("Generating report: " + name);
        return true;
    }

    public String getName() {
        return name;
    }
}
