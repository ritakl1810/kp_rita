package by.controller;

import by.builder.*;
import by.model.User;
import by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/user/user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }



    @PostMapping("/test-results")
    public String testResults(@RequestParam("first")String first,
                              @RequestParam("second")String second,
                              @RequestParam("third")String third,
                              @RequestParam("forth")String forth,
                              @RequestParam("fifth")String fifth,
                              @RequestParam("sixth")String sixth,
                              @RequestParam("seventh")String seventh,
                              @RequestParam("eights")String eights,
                              @RequestParam("ninth")String ninth,
                              Model model){

        int aCount = 0, bCount=0, cCount=0;
        List<String> answers = new ArrayList<String>();
        answers.add(first);
        answers.add(second);
        answers.add(third);
        answers.add(forth);
        answers.add(fifth);
        answers.add(sixth);
        answers.add(seventh);
        answers.add(eights);
        answers.add(ninth);

        for(int i=0;i<answers.size();i++){
            if(answers.get(i).equals("a"))aCount++;
            if(answers.get(i).equals("b"))bCount++;
            if(answers.get(i).equals("c"))cCount++;
        }

        int []arr = new int[]{aCount,bCount,cCount};
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++){
            if(max<arr[i]){
                maxIndex = i;
            }
        }
        String result = "";


        String testResult = "";

        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(result));
            while ((strCurrentLine = objReader.readLine())!=null) {
                testResult+=strCurrentLine;
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
        model.addAttribute("answer", testResult);
        return "/customer/other/test-results";
    }



    @PostMapping("/update-about-us")
    public String updateAboutUs(@RequestParam("about-us")String aboutUs) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\maria\\OneDrive\\Рабочий стол\\about-us.txt", false);
        writer.write(aboutUs);
        writer.flush();
        return "redirect:/admin-profile";
    }

    @GetMapping("profile1")
    public String profile(){return "/customer/profile/profile";}

}
