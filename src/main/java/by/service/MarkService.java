package by.service;

import by.model.Comment;
import by.repository.CustomerRepository;
import by.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {
    private final RateRepository rateRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public MarkService(RateRepository rateRepository, CustomerRepository customerRepository){
        this.rateRepository = rateRepository;
        this.customerRepository = customerRepository;
    }


}
