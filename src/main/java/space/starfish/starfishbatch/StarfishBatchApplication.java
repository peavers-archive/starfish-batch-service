package space.starfish.starfishbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class StarfishBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarfishBatchApplication.class, args);
	}
}
