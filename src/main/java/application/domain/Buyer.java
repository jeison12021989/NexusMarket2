package application.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A buyer never manages other buyers' information nor inventory.
 */
public class Buyer extends User {

    private final List<Address> addresses = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private Cart cart;

    public Buyer(Long identifier, String name, String email, String document) {
        super(identifier, name, email, document);
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
