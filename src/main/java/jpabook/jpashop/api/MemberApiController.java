package jpabook.jpashop.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberServie ;
	
	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1( @RequestBody @Valid Member member) {
		Long id = memberServie.join(member);
		return new CreateMemberResponse(id);
	}
	@PostMapping("/api/v2/members")
	public CreateMemberResponse saveMemberV2( @RequestBody @Valid CreateMemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());
		
		Long id = memberServie.join(member);
		
		return new CreateMemberResponse(id);
	}
	
	@Data
	static class CreateMemberRequest{
		private String name ;
	}

	@Data
	static class CreateMemberResponse{
		private Long id; 
		
		public CreateMemberResponse(Long id) {
			this.id = id;
		}
	}

}
