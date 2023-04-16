package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;
	private String serverUrl = "http://localhost:8080/accounts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create an account
		restTemplate.postForLocation(serverUrl+"?accountNumber=667&accountHolder='Joe Smith'",null);
		// create an account
		restTemplate.postForLocation(serverUrl+"?accountNumber=559&accountHolder='Mary Jones'",null);
        // get account
		AccountDTO account= restTemplate.getForObject(serverUrl+"/667", AccountDTO.class );
		System.out.println("--------------------Account info from 667----------------------");
		System.out.println(account);
		// deposit
		restTemplate.postForLocation(serverUrl+"?accountNumber=667&operation='deposit'&amount=122.25",null);
		// withdraw
		restTemplate.postForLocation(serverUrl+"?accountNumber=667&operation='withdraw'&amount=2.25",null);

		// get account
		account= restTemplate.getForObject(serverUrl+"/667", AccountDTO.class );
		System.out.println("--------------------Account info from 667----------------------");
		System.out.println(account);

		//delete an account
		restTemplate.delete(serverUrl+"/559");

	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
