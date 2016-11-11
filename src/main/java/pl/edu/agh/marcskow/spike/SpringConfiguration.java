package pl.edu.agh.marcskow.spike;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.agh.marcskow.spike.repositories")
@EntityScan(basePackages = "pl.edu.agh.marcskow.spike.entities")
public class SpringConfiguration {
}
