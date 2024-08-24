package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;


@Service
@Transactional( readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepo;

	// 회원 가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepo.save(member);		
		return member.getId();
	}
	public void validateDuplicateMember(Member member) {
		// 중복 예외
		List<Member> findMembers =  memberRepo.findByName(member.getName());
		if( !findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepo.findAll();		
	}
	
	// 회원 한 명 조회
	public Member findOne(Long memberId) {
		return memberRepo.findOne(memberId);		
	}
	
	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepo.findOne(id);
		member.setName(name);
		
	}
	
}
