package application.service;

import application.domain.models.Seller;
import application.exception.BusinessRuleException;
import application.repository.SellerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller registerSeller(String name, String email, String document) {
        Seller seller = new Seller(name, email, document);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findSeller(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new BusinessRuleException("Seller " + sellerId + " does not exist."));
    }
}
