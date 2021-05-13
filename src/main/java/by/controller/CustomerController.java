package by.controller;

import by.Strategy.*;
import by.model.Customer;
import by.password.ForgotPasswordClass;
import by.service.AdminService;
import by.service.CustomerService;
import by.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    EmailValidator validator = new EmailValidator();
    private final CustomerService customerService;
    private final AdminService adminService;

    private String errorMess;

    @Autowired
    public CustomerController(CustomerService customerService, AdminService adminService) {
        this.customerService = customerService;
        this.adminService = adminService;
    }

    @GetMapping("/customers")
    public String findAll(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/admin/customer/customer-list";
    }

    @GetMapping("/auth")
    public String createCustomerForm(Customer customer){
        return "/authorization/auth";
    }

    @GetMapping("/auth-error")
    public String createCustomerFormWithError(Customer customer, Model model){
        model.addAttribute("error",errorMess);
        return "/authorization/auth";
    }

    @PostMapping("/create-customer")
    public String createCustomer(Customer customer){

        for(char c : customer.getName().toCharArray()){
            if(Character.isDigit(c)){
                ErrorStrategy errorStrategy = new ErrorName();
                errorMess = errorStrategy.validRegistration();
                return "redirect:/auth-error";
            }
            if(!Character.isAlphabetic(c)) {
                ErrorStrategy errorStrategy = new ErrorName();
                errorMess = errorStrategy.validRegistration();
                return "redirect:/auth-error";
            }
        }
        if(!validator.validate(customer.getEmail())){
            ErrorStrategy errorStrategy = new ErrorEmail();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";
        }
        if(customer.getAccess().length()<6){
            ErrorStrategy errorStrategy = new ErrorPassword();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";
        }

        customerService.saveCustomer(customer);



        return "/customer/profile/profile";
    }

    @PostMapping("/entrance")
    public String entrance(Customer customer){

        if(adminService.findByNameAndAccess(customer.getEmail(),customer.getAccess()).size()!=0)
            return "redirect:/admin-profile";

        if(customerService.findByEmail(customer.getEmail(),customer.getAccess()).size()!=0)
            return "/customer/profile/profile";
        ErrorStrategy errorStrategy = new ErrorAuthorization();
        errorMess = errorStrategy.validRegistration();
        return "redirect:/auth-error";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(Customer customer){
        List<Customer> customers = customerService.findByEmail2(customer.getEmail());
        if(customers.size()!=0){
            ForgotPasswordClass.mail(customer.getEmail(),customers.get(0).getAccess(), customers.get(0).getName());

            ErrorStrategy errorStrategy = new ErrorWarning();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";

        }
        ErrorStrategy errorStrategy = new ErrorInvalidEmail();
        errorMess = errorStrategy.validRegistration();
        return "redirect:/auth-error";
    }

}
