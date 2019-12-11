package <%= domain %>.<%= host %>.<%= project %>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class <%= className %>Application {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}
}
