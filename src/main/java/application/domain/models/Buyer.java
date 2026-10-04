package application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain 2 - Buyer Management.
 * A buyer never manages other buyers' information nor inventory
 * (Key Restriction, Domain 2).
 */
@Entity
@DiscriminatorValue("BUYER")
public class Buyer extends User {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "buyer_id")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Cart cart;

    protected Buyer() {
        super();
    }

    public Buyer(String name, String email, String document) {
        super(name, email, document);
    }

    @Override
    public String showRole() {
        return "Buyer";
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
