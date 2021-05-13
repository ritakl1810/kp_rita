package by.controller;

import by.model.Comment;
import by.model.Rate;
import by.service.TicketService;
import by.service.RateService;
import by.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RateController {
    private final MarkService markService;
    private final RateService rateService;
    private final TicketService ticketService;

    @Autowired
    public RateController(MarkService markService, RateService rateService, TicketService ticketService) {
        this.markService = markService;
        this.rateService = rateService;
        this.ticketService = ticketService;
    }

    @GetMapping("/comments")
    public String commentsAdmin(Model model){
        List<Comment> comments = markService.findAll();
        model.addAttribute("comments",comments);
        return "/admin/other/comments";
    }

    @GetMapping("/rates")
    public String ratesAdmin(Model model){
        List<Rate> rates = rateService.findAll();
        model.addAttribute("rates",rates);
        return "/admin/other/rates";
    }

    @GetMapping("/customer-rate")
    public String rate(){
        return "/customer/other/customer-rate";
    }

    @PostMapping("/comment-us")
    public String comments(@RequestParam("user-name")String login, @RequestParam("comment")String comment){
        markService.saveComment(login,comment);

        return "redirect:/customer-rate";
    }

    @PostMapping("/rate-us")
    public String rate(@RequestParam("rating")String rating, @RequestParam("user-name")String login){
        rateService.saveComment(rating,login);
        return "redirect:/customer-rate";
    }

}
