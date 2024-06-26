package jpabook.jpashop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;


	@Test
	@Rollback(false)
	public void 회원가입() throws Exception{

		// given
		Member member = new Member();
		member.setName("hong2");

		// when
		Long saveId = memberService.join(member);
		// then
		assertEquals(member, memberRepository.findOne(saveId));

	}

	@Test()
	public void 중복_회원_예외() throws Exception{

		// given
		Member member1 = new Member();
		member1.setName("hong1");

		Member member2 = new Member();
		member2.setName("hong1");

		// when
		memberService.join(member1);
		try {
			memberService.join(member2); // 여기서 예외가 발생해야한다 
		} catch (IllegalStateException e) {
			return ;
		}
		// then
		fail("예외가 발생해야한다.");

	}

}
