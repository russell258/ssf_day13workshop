package sg.nus.iss.visa.ssf.day13_workshop.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            pw.write(contact.getId());
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

    public void getAllContactInURL(Model model, String dataDir){
        // why use a set?
        Set<String> dataFiles = listFiles(dataDir);
        Set<String> modifiedFiles = new HashSet<String>();
        for (String file: dataFiles){
            String modifiedFile = file.replace(".txt", "");
            modifiedFiles.add(modifiedFile);
        }
        model.addAttribute("contact", modifiedFiles.toArray(new String [dataFiles.size()]));
    }

    private Set<String> listFiles(String dataDir){
        return Stream.of(new File(dataDir).listFiles()).filter(file->!file.isDirectory()).map(File::getName).collect(Collectors.toSet());
    }

}
