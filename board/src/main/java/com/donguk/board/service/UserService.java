package com.donguk.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.dto.user.GetUserResponseDTO;
import com.donguk.board.dto.user.PatchUserDTO;
import com.donguk.board.dto.user.PostUserDTO;
import com.donguk.board.dto.user.ResultResponseDTO;
import com.donguk.board.entity.MemberEntity;
import com.donguk.board.repository.MemberRepository;

@Service
public class UserService {

	@Autowired MemberRepository memberRepository;
	
	public ResponseDTO<List<GetUserResponseDTO>> getAllUser(){
		List<MemberEntity> memberList = memberRepository.findAll();
		
		List<GetUserResponseDTO> data = new ArrayList<GetUserResponseDTO>();
		
		for(MemberEntity member: memberList) {
			data.add(new GetUserResponseDTO(member));
		}
		
		return ResponseDTO.setSuccess("get User List Success", data);
	}
	
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
	
	public ResponseDTO<ResultResponseDTO> postUser(PostUserDTO dto) {

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
		
		return ResponseDTO.setSuccess("회원가입에 성공했습니다.", new ResultResponseDTO(true));
	}

	public ResponseDTO<GetUserResponseDTO> patchUser(PatchUserDTO dto) {
		// dto에서 이메일을 가져온다
		
		String email = dto.getEmail();
		MemberEntity member = null;
		
		// repository를 이용해서 데이터베이스에 있는 member테이블 중
		// email에 해당하는 데이터를 불러온다.
		try {
			member = memberRepository.findById(email).get();
		}catch(Exception e){
			// 만약 존재하지 않으면 Failed Response로 "Not Exist User" 반환
			return ResponseDTO.setFailed("Not Exist User");
		}
		
		// Request Body로 받은 nickname과 profile로 각각 변경
		member.setNickname(dto.getNickname());
		member.setProfile(dto.getProfile());
		
		// 변경한 Entity를 Repository를 이용해서 데이터베이스에 적용(저장)
		memberRepository.save(member);
		
		// 결과를 ResponseDTO에 담아서 반환
		return ResponseDTO.setSuccess("user patch success", new GetUserResponseDTO(member));
	}

	public ResponseDTO<ResultResponseDTO> deleteUser(String email) {
		// Repository를 이용해서 데이터베이스의 Member테이블 중 email에 해당하는 데이터를 삭제
		memberRepository.deleteById(email);
		
		return ResponseDTO.setSuccess("삭제가 완료됬습니다.", new ResultResponseDTO(true));
	}

}