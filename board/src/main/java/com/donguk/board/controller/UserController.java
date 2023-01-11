package com.donguk.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.dto.user.GetUserResponseDTO;
import com.donguk.board.dto.user.PatchUserDTO;
import com.donguk.board.dto.user.PostUserDTO;
import com.donguk.board.dto.user.ResultResponseDTO;
import com.donguk.board.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {

	@Autowired UserService userService;
	
	@PostMapping("")
	public ResponseDTO<ResultResponseDTO> postUser(@RequestBody PostUserDTO requestbody){
		return userService.postUser(requestbody);
	}
	
	@GetMapping("{email}")
	public ResponseDTO<GetUserResponseDTO> getUser(@PathVariable("email") String email) {
		return userService.getUser(email);
	}
	
	@PatchMapping("")
	public ResponseDTO<GetUserResponseDTO> PatchUser(@RequestBody PatchUserDTO requestbody){
		
		return userService.patchUser(requestbody);
	}
	
	@DeleteMapping("{email}")
	public ResponseDTO<?> deleteUser(@PathVariable("email") String email){
		
		return userService.deleteUser(email);
	}
}

