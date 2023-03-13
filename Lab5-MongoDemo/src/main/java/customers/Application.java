package customers;

import customers.entity.Address;
import customers.entity.CreditCard;
import customers.entity.Customer;
import customers.entity.Student;
import customers.repository.CustomerRepository;
import customers.repository.StudentRepository;
import customers.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private  StudentRepository studentRepository;
	@Autowired
	private StudentService studentService;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Create Student
		Address johnAddress = new Address("1000N 4th Street","Fairfield","52557");
		Address miraAddress = new Address("1000N 5th Street","Fairfield","52257");
		Address zyanAddress = new Address("Chicago Street","Seattle", "98118");
		Student john = new Student(101l,"John","987654320","john@gmail.com",johnAddress);
		Student mira = new Student(102l,"Mira","987654321","mira@gmail.com",miraAddress);
		Student zyan = new Student(103l,"Zyan","987654322","zyan@gmail.com",zyanAddress);

		List<Student> studentList = Arrays.asList(john,mira,zyan);
		studentRepository.saveAll(studentList);
		//Get all student

		List<Student> students = studentService.getAllStudents();
		System.out.println("****** All Student *******");
		for(Student student: students){
			System.out.println(student);
		}
		//Get all student by Name
		List<Student> studentListByName = studentService.getAllStudentsByName("Zyan");
		System.out.println("****** By Student Name *******");
		for(Student student: studentListByName){
			System.out.println(student);
		}
		//Get student by phonenumber
		Student studentByPhone = studentService.getStudentByPhoneNumber("987654322");
		System.out.println("****** By PhoneNumber *******");
		System.out.println(studentByPhone);

		List<Student> studentListByCity = studentService.getAllStudentByCity("Fairfield");
		System.out.println("****** By City *******");
		for(Student student: studentListByCity){
			System.out.println(student);
		}

        // create customer
		/*
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findCustomerWithCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}
		*/
	}

}
