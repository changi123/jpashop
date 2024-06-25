package jpabook.jpashop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {
	
	@Autowired
	MemberRepository memberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);

	}

}
