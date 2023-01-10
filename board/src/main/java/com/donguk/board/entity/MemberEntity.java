package com.donguk.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="member")
@Table(name="member")
public class MemberEntity {
	
	@Id
	private String email;
	private String password;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;
}	
