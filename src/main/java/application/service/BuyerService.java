package application.service;

import application.domain.models.Buyer;

public interface BuyerService {

    Buyer registerBuyer(String name, String email, String document);

    Buyer findBuyer(Long buyerId);
}
