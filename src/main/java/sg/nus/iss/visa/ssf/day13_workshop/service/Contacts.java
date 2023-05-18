package sg.nus.iss.visa.ssf.day13_workshop.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        String fileName = contact.getId();

        try {
            FileWriter fw = new FileWriter(dataDir + File.separator + fileName + ".txt");

            PrintWriter pw = new PrintWriter(fw);
            // pw.write(contact.getId());
            //commented out due to retrieving the contact list and individualcontact method
            pw.write(contact.getName()+"\n");
            pw.write(contact.getEmail()+"\n");
            pw.write(contact.getPhoneNumber()+"\n");
            pw.write(contact.getDob().toString()+"\n");

            pw.flush();
            model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.getDob()));
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Contact getContactbyId(String contactId, String dataDir){
        Contact ctc = new Contact();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        java.nio.file.Path filePath = new File(dataDir+"/"+contactId+".txt").toPath();
        Charset charset = Charset.forName("UTF-8");
        List<String> stringList = new ArrayList<String>();
        try {
            stringList = Files.readAllLines(filePath,charset);
            ctc.setId(contactId);
            ctc.setName(stringList.get(0));
            ctc.setEmail(stringList.get(1));
            ctc.setPhoneNumber(stringList.get(2));
            LocalDate dob = LocalDate.parse(stringList.get(3),formatter);
            ctc.setDob(dob);
            System.out.println("meow meow meow get Id");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        return ctc;
    }

    public void getAllContactInURL(Model model, String dataDir){
        // why use a set?
        Set<String> dataFiles = listFiles(dataDir);
        Set<String> modifiedFiles = new HashSet<String>();
        for (String file: dataFiles){
            String modifiedFile = file.replace(".txt", "");
            System.out.println(modifiedFile);
            modifiedFiles.add(modifiedFile);
            System.out.println("woof woof woof getting all contacts");
        }
        model.addAttribute("contacts", modifiedFiles.toArray(new String [dataFiles.size()]));
    }

    private Set<String> listFiles(String dataDir){
        return Stream.of(new File(dataDir).listFiles()).filter(file->!file.isDirectory()).map(File::getName).collect(Collectors.toSet());
    }

}
