package jpabook.jpashop.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberServie ;
	
	@GetMapping("/api/v1/members")
	public List<Member> membersV1(){
		return memberServie.findMembers();
	}
	
	@GetMapping("/api/v2/members")
	public Result membersV2(){
		List<Member> findMembers = memberServie.findMembers();
		List<MemberDto> collect = findMembers.stream()
				.map(m-> new MemberDto(m.getName()))
				.collect(Collectors.toList());
		
		return new Result(collect.size(), collect);
	}
	
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
	
	@PutMapping("api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2 (
			@PathVariable("id") Long id,
			@RequestBody @Valid UpdateMemberRequest request){
		
		memberServie.update(id, request.getName());
		Member findMember = memberServie.findOne(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName());
		
	}
	@Data
	@AllArgsConstructor
	static class Result<T>{
		private int count;
		private T data ;
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String name;
	}
	
	
	@Data
	static class UpdateMemberRequest{
		private String name ;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse{
		private Long id;
		private String name ;
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
