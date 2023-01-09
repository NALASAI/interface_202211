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
	
	public ResponseDTO<LoginDTO> login(AuthPostDTO dto){
		LoginDTO result = new LoginDTO("JWT", 360000);
		
		return ResponseDTO.setSuccess("", result);
	}
	
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
}
