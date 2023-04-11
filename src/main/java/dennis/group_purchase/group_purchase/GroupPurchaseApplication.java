package dennis.group_purchase.group_purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"dennis.group_purchase.group_purchase"})
@EnableJpaRepositories(basePackages = "dennis.group_purchase.group_purchase.repository")
public class GroupPurchaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupPurchaseApplication.class, args);
	}

}
