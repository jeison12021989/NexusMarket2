package application.service;

import application.domain.models.Buyer;

/**
 * Domain 2 - Buyer Management. Unlike sellers, buyers register
 * themselves.
 */
public interface BuyerService {

    Buyer registerBuyer(String name, String email, String document);

    Buyer findBuyer(Long buyerId);
}
