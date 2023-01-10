package com.donguk.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.dto.user.GetUserResponseDTO;
import com.donguk.board.dto.user.PostUserDTO;
import com.donguk.board.dto.user.PostUserResponseDTO;
import com.donguk.board.entity.MemberEntity;
import com.donguk.board.repository.MemberRepository;

@Service
public class UserService {

	@Autowired MemberRepository memberRepository;
	
	public ResponseDTO<GetUserResponseDTO> getUser(String email){
		// 해당 이메일을 데이터베이스에서 검색
		
		MemberEntity member;
		try {
			member = memberRepository.findById(email).get();
		}catch(Exception e) {
			// 존재하지 않으면 "Not Exist User" 메시지를 포함한 Failed Response 반환
			return ResponseDTO.setFailed("Not Exist User");
		}
		// 존재하면 User정보반환
		// 방법 1
//		GetUserResponseDTO responseData = new GetUserResponseDTO();
//		responseData.setEmail(member.getEmail());
//		responseData.setNickname(member.getNickname());
//		responseData.setProfile(member.getProfile());
//		responseData.setTelNumber(member.getTelNumber());
//		responseData.setAddress(member.getAddress());
//		
//		return ResponseDTO.setSuccess("Get User Success", responseData);
		
		// 방법 2
//		GetUserResponseDTO responseData =
//				GetUserResponseDTO
//				.builder()
//				.email(member.getEmail())
//				.nickname(member.getNickname())
//				.profile(member.getProfile())
//				.telNumber(member.getTelNumber())
//				.address(member.getAddress())
//				.build();
//		return ResponseDTO.setSuccess("Get User Success", responseData);
		
		// 방법 3
//		return ResponseDTO.setSuccess("Get User Success",
//				new GetUserResponseDTO(
//						member.getEmail(),
//						member.getNickname(),
//						member.getProfile(),
//						member.getTelNumber(),
//						member.getAddress()
//						)
//				);
		
		return ResponseDTO.setSuccess("Get User Success", new GetUserResponseDTO(member));
	}
	
	public ResponseDTO<PostUserResponseDTO> postUser(PostUserDTO dto) {

		// 데이터베이스에 해당 이메일이 존재하는지 체크
		// * 존재한다면 Failed Response를 반환
		// select * from member where email

		String email = dto.getEmail();
		
		try {
			if(memberRepository.existsById(email))
				return ResponseDTO.setFailed("이미 존재하는 이메일입니다");
		}catch(Exception e) {
			return ResponseDTO.setFailed("데이터베이스 오류입니다.");
		}
		
//		패스워드 비교
		String password = dto.getPassword();
		String password2 = dto.getPassword2();
		
		if(!password.equals(password2)) {
			return ResponseDTO.setFailed("비밀번호가 서로 다릅니다");
		}
		
//		DB에 저장
		MemberEntity member = MemberEntity.builder()
				.email(dto.getEmail())
				.password(dto.getPassword())
				.nickname(dto.getNickname())
				.telNumber(dto.getTelphone())
				.address(dto.getAddress() + " " + dto.getAddressDetail())
				.build();
		memberRepository.save(member);
		// JpaRepository.save(Entity) 메서드
		// - 해당 Entity Id가 데이터베이스 테이블에 존재하지 않으면
		// Entity Insert 작업을 수행
		// * 해당 Entity Id가 데이터테이블에 존재하면 존재하는 Entity를 UPDATE작업을 수행
		
		return ResponseDTO.setSuccess("회원가입에 성공했습니다.", new PostUserResponseDTO(true));
	}

}