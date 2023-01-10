package com.donguk.board.dto.user;

import com.donguk.board.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class GetUserResponseDTO {
	private String email;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;
	
	public GetUserResponseDTO(MemberEntity member) {
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.profile = member.getProfile();
		this.telNumber = member.getTelNumber();
		this.address = member.getAddress();
	}
}
