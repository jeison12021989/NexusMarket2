package application.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String address;

    protected Address() {
        // required by JPA
    }

    public Address(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public void showAddress() {
        System.out.println(address + ", " + city);
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
