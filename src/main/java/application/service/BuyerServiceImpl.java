package application.service;

import application.domain.models.Buyer;
import application.exception.BusinessRuleException;
import application.repository.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final AtomicLong sequence = new AtomicLong(1);

    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Buyer registerBuyer(String name, String email, String document) {
        Buyer buyer = new Buyer(sequence.getAndIncrement(), name, email, document);
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer findBuyer(Long buyerId) {
        return buyerRepository.findById(buyerId)
                .orElseThrow(() -> new BusinessRuleException("Buyer " + buyerId + " does not exist."));
    }
}
