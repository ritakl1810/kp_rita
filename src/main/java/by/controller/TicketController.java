package by.controller;

import by.Strategy.ErrorCorrectDate;
import by.Strategy.ErrorDate;
import by.Strategy.ErrorStrategy;
import by.model.Reis;
import by.model.Customer;
import by.model.Ticket;
import by.service.ReisService;
import by.service.CustomerService;
import by.service.TicketService;
import by.validators.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketController {

    private String errorMessage = "";
    private final TicketService ticketService;
    private final ReisService reisService;
    private final CustomerService customerService;
    private String departureCountry;
    private String destinationCountry;

    @Autowired
    public TicketController(TicketService ticketService, ReisService reisService, CustomerService customerService) {
        this.ticketService = ticketService;
        this.reisService = reisService;
        this.customerService = customerService;
    }

    @PostMapping("/get-all-my-tickets")
    public String findAllTickets(@RequestParam("login")String customer, Model model){
        List<Customer> customer1 = customerService.findByEmail2(customer);

        List<Ticket> tickets = ticketService.findAllByCustomerId(customer1.get(0));

        model.addAttribute("tickets", tickets);
        return "/customer/tickets-list";
    }

    @GetMapping("/tickets")
    public String findAll(Model model){
        return "/customer/tickets-list";
    }

    @GetMapping("/new-tickets")
    public String createTicketForm(Model model){
        List<Reis> tickets = reisService.findAllSorted("asc");
        model.addAttribute("tickets", tickets);
        List<Reis> tickets2 = reisService.findAllSorted("asc");
        model.addAttribute("tickets2", tickets2);
        model.addAttribute("error", errorMessage);
        return "/customer/tickets-create";
    }


    @PostMapping("/new-tickets-create")
    public String createTicket(@RequestParam("login")String customer{

        ErrorStrategy errorStrategy = null;
        if(departureCity.equals(destinationCity)){
            errorStrategy = new ErrorCity();
            errorMessage = errorStrategy.validRegistration();
            return "redirect:/new-tickets";
        }

        if(departureDate.compareTo(Date.dateNow())<0 || arrivalDate.compareTo(Date.dateNow())<0){
            errorStrategy = new ErrorCorrectDate();
            errorMessage = errorStrategy.validRegistration();
            return "redirect:/new-tickets";
        }

        if(departureDate.compareTo(arrivalDate)>=0){
            errorStrategy = new ErrorDate();
            errorMessage = errorStrategy.validRegistration();
            return "redirect:/new-tickets";
        }

        Ticket ticket = new Ticket();

        List<Customer> customer1 = customerService.findByEmail2(customer);

        ticket.setId_customer(customer1.get(0));

        Reis departureReisObject = reisService.findIdOfTheCity(departureCity);

        ticketService.saveDestination(ticket);

        return "redirect:/book-tickets";
    }

    @GetMapping("/top-five-tickets")
    public String findBetter(Model model){
        List<Ticket> tickets = ticketService.findTopFive();
        tickets.add(ticketService.recommend().get(0));
        model.addAttribute("tickets", tickets);
        return "/customer/tickets/top-five";
    }



}
