package com.donguk.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String email;
	private String nickname;
	private String profile;
	private String telphone;
	private String address;
	private String addressDetail;
}
