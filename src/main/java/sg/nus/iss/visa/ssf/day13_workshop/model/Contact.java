package sg.nus.iss.visa.ssf.day13_workshop.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    
    @NotNull(message = "Name can not be empty")
    @Size(min = 3, max = 64, message = "Name should be between 3 to 64 characters")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 7, message = "Minimum 7 digits")
    private String phoneNumber;

    //one more for cannot be younger than 10 years old or older than 100... custom constraint?
    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth cannot be in the future")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dob;

    @Min(value = 10, message = "Must be above 10 years old")
    @Max(value = 100, message = "Must be below 100 years old")
    private int age;

    //should have a private final string id with uuid generator to add into constructors too.
    private String id;

    public Contact() {
        this.id = generateId();
    }

    public Contact(String id, String name, String email, String phoneNumber, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    // any difference between this vs UUID?
    private String generateId(){
        Random r =  new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() <8 ) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0,8);
    }
    
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        //calculate age
        int calculatedAge=0;
        if (dob!=null){
            calculatedAge = Period.between(dob,LocalDate.now()).getYears();
        }

        this.age = calculatedAge;
        this.dob=dob;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dob=" + dob + "]";
    }
}
