package com.donguk.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="set")
// staticName이라는 옵션을 사용하여 생성자를 private으로 생성하고, private 생성자를 감싸고 있는 static factory 메소드를 추가할 수 있다.
public class ResponseDTO<D> {
	private boolean status;
	private String message;
	private D data;
	
	public static <D> ResponseDTO<D> setSuccess(String message, D data){
		return ResponseDTO.set(true, message, data);
	}
	
	public static <D> ResponseDTO<D> setFailed(String message){
		return ResponseDTO.set(false, message, null);
	}
}