package test.api.gps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.api.gps.entity.PointOfInterest;
import test.api.gps.repository.PointOfInterestRepository;

@SpringBootApplication
public class GpsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}

	@Autowired
	private PointOfInterestRepository repository;

	@Override
	public void run(String... args) throws Exception {

	}
}
