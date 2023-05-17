package sg.nus.iss.visa.ssf.day13_workshop.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
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
    @Past(message = "Date of birth cannot be in the future")
    private DateTimeFormat dob;

    //should have a private final string id with uuid generator to add into constructors too.

    public Contact() {
    }

    public Contact(String name, String email, String phoneNumber, @Past(message = "Date of birth cannot be in the future") DateTimeFormat dob) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
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
    public @Past(message = "Date of birth cannot be in the future") DateTimeFormat getDob() {
        return dob;
    }
    public void setDob(@Past(message = "Date of birth cannot be in the future") DateTimeFormat dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dob=" + dob + "]";
    }
}
