package by.service;

import by.model.Customer;
import by.model.Ticket;
import by.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findBetter(){

        return ticketRepository.findTopFive(PageRequest.of(0,5));
    }

    public List<Ticket> findAllByCustomerId(Customer id){
        return ticketRepository.findDestinationById_customer(id);
    }

    public Ticket findById(Integer id){
        return ticketRepository.getOne(id);
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public void deleteById(Integer id){
        ticketRepository.deleteById(id);
    }
}
