package sg.nus.iss.visa.ssf.day13_workshop.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sg.nus.iss.visa.ssf.day13_workshop.model.Contact;

@Service
public class Contacts {
    
    public void save(Contact contact, Model model, String dataDir){
        //change to ID later on after generated. also need to add ID for addAttribute to model.
        String fileName = contact.getName();

        try {
            FileWriter fw = new FileWriter(dataDir + File.separator + fileName + ".txt");

            PrintWriter pw = new PrintWriter(fw);
            pw.write(contact.getName());
            pw.write(contact.getEmail());
            pw.write(contact.getPhoneNumber());
            pw.write(contact.getDob().toString());

            pw.flush();
            model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.getDob()));
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
