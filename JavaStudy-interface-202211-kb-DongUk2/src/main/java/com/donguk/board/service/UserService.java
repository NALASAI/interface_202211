package com.donguk.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.dto.user.PostUserDTO;
import com.donguk.board.dto.user.PostUserResponseDTO;
import com.donguk.board.entity.MemberEntity;
import com.donguk.board.repository.MemberRepository;

@Service
public class UserService {

	@Autowired MemberRepository memberRepository;
	
	public ResponseDTO<PostUserResponseDTO> postUser(PostUserDTO dto) {

		String password = dto.getPassword();
		String password2 = dto.getPassword2();
		
		if(!password.equals(password2)) {
			return ResponseDTO.setFailed("비밀번호가 서로 다릅니다");
		}
		
		MemberEntity member = MemberEntity.builder()
				.email(dto.getEmail())
				.password(dto.getPassword())
				.nickname(dto.getNickname())
				.telNumber(dto.getTelphone())
				.address(dto.getAddress() + " " + dto.getAddressDetail())
				.build();
		
		memberRepository.save(member);
		
		

		
		return ResponseDTO.setSuccess("회원가입에 성공했습니다.", new PostUserResponseDTO(true));
	}

}