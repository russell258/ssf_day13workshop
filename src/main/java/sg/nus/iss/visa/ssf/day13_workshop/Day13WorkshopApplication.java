package sg.nus.iss.visa.ssf.day13_workshop;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.iss.visa.ssf.day13_workshop.utility.Utility;

@SpringBootApplication
public class Day13WorkshopApplication {

    public static void main(String[] args) {
		
		// mvn spring-boot:run "-Dspring-boot.run.arguments=--dataDir=/opt/tmp/data"
		// USE ALREADY EXISTING METHOD --> optVal. 
		// File naming should be in controller
		// how to pass argument from CLI to variable to other classes?
		SpringApplication app = new SpringApplication(Day13WorkshopApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsValList = appArgs.getOptionValues("dataDir");
		if (opsValList!=null && opsValList.size()>0){
			String opsVal = opsValList.get(0);
			Utility.createDir(opsVal);
		}else{
			System.err.println("Directory not selected.");
			System.exit(0);
		}
		app.run(args);
	}	
}
