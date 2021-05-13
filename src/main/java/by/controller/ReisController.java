package by.controller;

import by.model.Reis;
import by.service.ReisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ReisController {

    private final ReisService reisService;
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/uploads";

    @Autowired
    public ReisController(ReisService reisService) {
        this.reisService = reisService;
    }

    @GetMapping("/cities")
    public String findAll(Model model){
        List<Reis> cities = reisService.findAll();
        model.addAttribute("cities", cities);
        return "/admin/reis/reis-list";
    }


    @GetMapping("/customer-reis-list")
    public String findAllFoCustomer(Model model){
        List<Reis> reis = reisService.findAll();
        model.addAttribute("reis", reis);
        return "customer/reis/reises";
    }

    @GetMapping("/reis-create")
    public String createReisForm(Reis reis){
        return "/admin/reis/reis-create";
    }

    @PostMapping("/reis-create")
    public String createReis(Reis reis, @RequestParam("file") MultipartFile file){

        try {

            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();

            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

            reis.setPicture("/images/uploads/"+fileName);

            reisService.saveCity(reis);

        } catch (Exception e) {
            e.printStackTrace();
        }

*/
        return "redirect:/reis";
    }

    @GetMapping("reis-delete/{id}")
    public String deleteReis(@PathVariable("id") Integer id){
        reisService.deleteById(id);

        return "redirect:/reiss";
    }

    @GetMapping("/reis-update/{id}")
    public String updateReis(@PathVariable("id") Integer id, Model model){
        Reis reis = reisService.findById(id);
        model.addAttribute("reis", reis);
        return "/admin/reis-update";
    }

    @PostMapping("/reis-update")
    public String updateReis(Reis reis){
        reisService.saveCity(reis);
        return "redirect:/reiss";
    }


    @PostMapping("/found-reis-for-admin")
    public String foundForAdmin(Reis reis, Model model){
        List<Reis> reis = reisService.findByName(reis.getName());
        model.addAttribute("reis", reis);
        return "/admin/reis-list";
    }

    @PostMapping("/found-reis")
    public String foundForCustomer(Reis reis, Model model){
        List<Reis> reis = reisService.findByName(reis.getName());
        model.addAttribute("reiss", reis);
        return "/customer/reis";
    }



    @GetMapping("/sort-asc")
    public String findAllASC(Model model){
        List<Reis> reis = reisService.findAllSorted("asc");
        model.addAttribute("reis", reis);
        return "/customer/reis";
    }

    @GetMapping("/sort-desc")
    public String findAllDESC(Model model){
        List<Reis> cities = reisService.findAllSorted("desc");
        model.addAttribute("reis", cities);
        return "/customer/reis";
    }

    @PostMapping("/reis-filter-input")
    public String findAllFilterInput(@Param("min")String min, @Param("max")String max, Model model){
        List<Reis> reis= reisService.findAllFiltered(min,max);
        model.addAttribute("reis", reis);
        return "/customer/reis";
    }

}
