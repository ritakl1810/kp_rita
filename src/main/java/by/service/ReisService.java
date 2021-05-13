package by.service;

import by.model.Reis;
import by.repository.ReisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReisService {
    private final ReisRepository reisRepository;

    @Autowired
    public ReisService(ReisRepository reisRepository){
        this.reisRepository = reisRepository;
    }

    public Reis findById(Integer id){
        return reisRepository.getOne(id);
    }

    public Reis findIdOfTheReis(String city){
        return reisRepository.findCityIdByName(city);
    }

    public List<Reis> findAll(){
        return reisRepository.findAll();
    }

    public List<Reis> findAllSorted(String order){
        if(order.equals("asc")) return reisRepository.findAll(Sort.by("cost").ascending());
        return reisRepository.findAll(Sort.by("cost").descending());
    }

    public List<Reis> findAllFiltered(String min, String max){

        return reisRepository.findAllFiltered(min, max);
    }

    public List<Reis> findByName(String name){
        return reisRepository.findAllByName(name);
    }

    public Reis saveCity(Reis reis){
        return reisRepository.save(reis);
    }

    public void deleteById(Integer id){
        reisRepository.deleteById(id);
    }
}
