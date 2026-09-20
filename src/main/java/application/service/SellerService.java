package application.service;

import application.domain.models.Seller;

/**
 * Domain 3 - Seller Management. Sellers cannot self-register; they are
 * onboarded by an Administrator (business rule).
 */
public interface SellerService {

    Seller registerSeller(String name, String email, String document);

    Seller findSeller(Long sellerId);
}
