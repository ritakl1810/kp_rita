package by.controller;

import by.model.Admin;
import by.model.Reis;
import by.model.Ticket;
import by.service.AdminService;
import by.service.TicketService;
import by.service.RateService;
import by.validators.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final TicketService ticketService;
    private final RateService rateService;

    @Autowired
    public AdminController(AdminService adminService, TicketService ticketService, RateService rateService) {
        this.adminService = adminService;
        this.ticketService = ticketService;
        this.rateService = rateService;
    }

    @GetMapping("/admins")
    public String findAll(Model model){
        List<Admin> admins = adminService.findAll();
        model.addAttribute("admins", admins);
        return "/admin/admin/admin-list";
    }

    @GetMapping("/admin-create")
    public String createAdminForm(Admin admin){
        return "/admin/admin/admin-create";
    }

    @PostMapping("/admin-create")
    public String createAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/admin-profile")
    public String showAdminProfile(Model model){
        BufferedReader objReader = null;
        String response = "";
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader("E:\\Учеба\\семестр_6\\курсoвая\\project-main\\about-us.txt"));
            while ((strCurrentLine = objReader.readLine())!=null) {
                response+=strCurrentLine+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        createReports();
        model.addAttribute("about", response);

        return "/admin/admin-profile";
    }

    public void createReports(){
        try(FileWriter writer = new FileWriter("E:\\Учеба\\семестр_6\\курсовая\\project-main\\first-report.txt", false))
        {
            // запись всей строки
            String text = "Аэропорт\n";
            text += "Отчет о рейтинге компании\n\n";
            text+= Date.dateNow() + "\n\n";
            text+="Наиболее популярные рейсы:\n";
            text+="==================================\n";
            List<Ticket> tickets = ticketService.findBetter();
            writer.write(text);

            writer.flush();


        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    @GetMapping("admin-delete/{id}")
    public String deleteReis(@PathVariable("id") Integer id){
        adminService.deleteById(id);

        return "redirect:/admins";
    }

    @GetMapping("/admin-update/{id}")
    public String updateReisForm(@PathVariable("id") Integer id, Model model){
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin/admin-update";
    }

    @PostMapping("/admin-update")
    public String updateReis(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/city-search-admin")
    public String findByNameLike(Reis reis){

        return "/admin/city/reis-search";
    }
}
