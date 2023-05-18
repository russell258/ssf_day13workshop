package sg.nus.iss.visa.ssf.day13_workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import sg.nus.iss.visa.ssf.day13_workshop.Day13WorkshopApplication;
import sg.nus.iss.visa.ssf.day13_workshop.model.Contact;
import sg.nus.iss.visa.ssf.day13_workshop.service.Contacts;

@Controller
@RequestMapping(path="/")
public class AddressBookController {
    
    @Autowired
    Day13WorkshopApplication utility;

    @Autowired
    Contacts service;
    
    //this is a hardcode style, not passing in the argument into the java classes. need to find out more
    @Value("${data.dir}")
    private String dataDir;

    //request method to load landing page
    //since this method is nested inside the request mapping path, the path to access should be localhost:8080/home/form
    @GetMapping
    public String showAddressBook(Model model){
        model.addAttribute("contact", new Contact());
        return "addressBook";
    }

    //creating a object from contact class/model and saving so should be post. Pathing /contact to be from contacts.html form action
    @PostMapping(path = "/contact")
    public String saveAddressBook(@Valid Contact contact, BindingResult bindingResult, Model model){
        System.out.println(contact.toString());

        if(bindingResult.hasErrors()){
            return "addressBook";  
        }

        service.save(contact, model, dataDir);
        model.addAttribute("successMessage", "Contact saved successfully, with status code: "+ HttpStatus.CREATED);
        return "showContact";
    }

    //need the get by ID first
    // @GetMapping(path = "/list")
    // public String getAllContacts(Model model) {
    //     service.getAllContactInUR(model, dataDir);
    //     return "contacts";
    // }
    
}
