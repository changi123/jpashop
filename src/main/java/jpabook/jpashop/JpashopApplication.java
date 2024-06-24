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

//		서비스에서 호출
//		Member member = new Member();
//		member.setUsername("memeberA");
//		Long saveId = memberRepository.save(member);
//		Member findMember = memberRepository.find(saveId);
//		System.out.println(findMember.toString());
	}

}
