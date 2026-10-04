package application.service;

import application.domain.models.Buyer;
import application.exception.BusinessRuleException;
import application.repository.BuyerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Buyer registerBuyer(String name, String email, String document) {
        Buyer buyer = new Buyer(name, email, document);
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer findBuyer(Long buyerId) {
        return buyerRepository.findById(buyerId)
                .orElseThrow(() -> new BusinessRuleException("Buyer " + buyerId + " does not exist."));
    }
}
