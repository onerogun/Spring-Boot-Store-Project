package og.prj.adminservice;

import og.prj.adminservice.jpafiles.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories
public class AdminServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
