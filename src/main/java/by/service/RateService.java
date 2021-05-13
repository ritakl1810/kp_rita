package by.service;

import by.model.Rate;
import by.repository.CustomerRepository;
import by.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {
    private final MarkRepository ratingRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RateService(MarkRepository ratingRepository, CustomerRepository customerRepository){
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
    }

    public List<Rate> findAll(){
        return ratingRepository.findAll();
    }

    public void saveComment(String rates, String login){
        Rate rate = new Rate();
        rate.setRates(rates);
        rate.setId_customer(customerRepository.findTopByEmail(login));
        ratingRepository.save(rate);
    }

    public Float findAverageRate(){
        return ratingRepository.findAverageRate();
    }

    public List<Integer> findRates(){
        return ratingRepository.findRatesCount();
    }
}
