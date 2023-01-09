package com.donguk.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donguk.board.dto.auth.AuthPostDTO;
import com.donguk.board.dto.auth.LoginDTO;
import com.donguk.board.dto.response.ResponseDTO;
import com.donguk.board.service.AuthService;


@RestController
@RequestMapping("api/auth/")
public class ExampleController {
	
	@Autowired AuthService authService;
	
	@PostMapping("")
	public ResponseDTO<LoginDTO> login(@RequestBody AuthPostDTO requestBody){
		
		if(requestBody != null) {
			return authService.login(requestBody);
		}else {
			return ResponseDTO.setFailed("login failed");
		}
	}
	
	@GetMapping("")
	public String hello() {
		return authService.hello();
	}
}
