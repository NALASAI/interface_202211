package com.donguk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donguk.board.dto.auth.AuthPostDTO;
import com.donguk.board.dto.auth.LoginDTO;
import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.entity.MemberEntity;
import com.donguk.board.repository.MemberRepository;

@Service
public class AuthService {

	@Autowired MemberRepository memberRepository;
	
	public String hello(){
		MemberEntity memberEntity = MemberEntity
				.builder()
				.email("ebyul96428@naver.com")
				.password("qwer1234!@")
				.nickname("ebyul96428")
				.telNumber("010-5220-4331")
				.address("부산")
				.build();
		
		memberRepository.save(memberEntity);
		
		MemberEntity savedMemberEntity = memberRepository.findById("ebyul96428@naver.com").get();
		
		List<MemberEntity> list = memberRepository.myFindAll("ebyul96428@naver.com");
		System.out.println(list.toString());
		
		return savedMemberEntity.getNickname();
	}
	
	
	public ResponseDTO<LoginDTO> login(AuthPostDTO dto){
		
		// 입력받은 Email 으로 데이터베이스에서 검색
		String email = dto.getEmail();
		MemberEntity memberEntity;

		// 존재하지 않는다면 없는 아이디 "로그인 실패"반환
		try {
			memberEntity = memberRepository.findById(email).get();
		} catch(Exception e) {
			return ResponseDTO.setFailed("로그인 실패");
		}

		// 존재한다면 입력받은 패스워드와 데이터베이스의 패스워드와 동일한지 검사
		// 동일하지 않다면 "로그인 실패 " 반환
		String password = dto.getPassword();
		String dbpassword = memberEntity.getPassword(); 
		if(!password.equals(dbpassword)) {
			return ResponseDTO.setFailed("로그인 실패");
		}
		
		// 토큰 생성 후 토큰 및 만료시간 반환
		LoginDTO result = new LoginDTO("JWT", 36000000);
		return ResponseDTO.setSuccess("", result);
	}
	
}
