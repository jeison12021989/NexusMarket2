package application.domain.models;

public class Address {

    private final String city;
    private final String address;

    public Address(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public void showAddress() {
        System.out.println(address + ", " + city);
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
