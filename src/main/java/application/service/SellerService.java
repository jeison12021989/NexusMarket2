package application.service;

import application.domain.models.Seller;

public interface SellerService {

    Seller registerSeller(String name, String email, String document);

    Seller findSeller(Long sellerId);
}
