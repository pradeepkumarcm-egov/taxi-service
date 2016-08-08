package org.egov.fuber;

import org.egov.fuber.entity.City;
import org.egov.fuber.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FuberApplication implements CommandLineRunner {

	@Autowired
	private CityRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(FuberApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		this.repository.deleteAll();
		saveCustomers();
		fetchAllCustomers();
		fetchIndividualCustomers();
	}

	private void saveCustomers() {
		this.repository.save(new City((long) 2, "Alice"));
		this.repository.save(new City((long) 3, "Delhi"));
	}

	private void fetchAllCustomers() {
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (City customer : this.repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
	}

	private void fetchIndividualCustomers() {
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByName("Alice"));

		System.out.println("Customers found with findByLastName('Delhi'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByName("Delhi"));
	}

}
