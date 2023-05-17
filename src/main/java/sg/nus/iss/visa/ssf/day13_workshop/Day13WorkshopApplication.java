package sg.nus.iss.visa.ssf.day13_workshop;

import java.io.File;
import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13WorkshopApplication {

	public static void main(String[] args) {

		// USE ALREADY EXISTING METHOD --> optVal. 
		// File naming should be in controller
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsValList = appArgs.getOptionValues("dataDir");
		if (opsValList!=null && opsValList.size()>0){
			String opsVal = opsValList.get(0);
		}else{
			System.err.println("Directory not selected.");
			System.exit(0);
		}
		
		SpringApplication.run(Day13WorkshopApplication.class, args);
	}

}
