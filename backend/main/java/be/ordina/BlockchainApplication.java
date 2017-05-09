package be.ordina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class BlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainApplication.class, args);
	}
}
