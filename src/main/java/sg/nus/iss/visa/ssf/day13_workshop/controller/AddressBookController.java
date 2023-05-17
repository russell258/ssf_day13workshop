package sg.nus.iss.visa.ssf.day13_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.visa.ssf.day13_workshop.model.Contact;

@Controller
@RequestMapping(path="/home")
public class AddressBookController {
    
    //request method to load landing page
    //since this method is nested inside the request mapping path, the path to access should be localhost:8080/home/form
    @GetMapping(path = "/form")
    public String showContacts(Model model){
        
        model.addAttribute("contact", new Contact());
        return "contacts";
    }

    //creating a object from contact class/model and saving so should be post. Pathing /contact to be from contacts.html form action
    @PostMapping(path = "/contact")
    public String addContacts(@Valid Contact contact, Model model){
        System.out.println(contact.toString());
        return "contacts";
    }
    
}
