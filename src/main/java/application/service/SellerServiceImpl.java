package application.service;

import application.domain.models.Seller;
import application.exception.BusinessRuleException;
import application.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final AtomicLong sequence = new AtomicLong(1);

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller registerSeller(String name, String email, String document) {
        Seller seller = new Seller(sequence.getAndIncrement(), name, email, document);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findSeller(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new BusinessRuleException("Seller " + sellerId + " does not exist."));
    }
}
