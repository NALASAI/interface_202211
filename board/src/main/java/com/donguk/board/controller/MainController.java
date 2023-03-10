package com.donguk.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donguk.board.dto.HelloDTO;

// Response로 HTML을 반환하는 Controller 가 아닌 
// Response Body에 직접 데이터를 담아서 응답하는 Controller
// @Controller + @ResponseBody
@RestController
// @RequestMapping(pattern) : http://localhost:4040/(end-point)/
// end-point의 패턴을 지정하여 해당 패턴의 end-point 일 때 행당 Controller를 실행한다.
@RequestMapping("apis")
public class MainController {
    // http방식://호스트/포트/~ (/~ : end-point)
    // http://localhost:4040/~~~~

	static final String HELLO = "hello";
	
    // @GetMapping(end-point) : 해당 end-point로 Get 방식의 Request가 왔을 때 동작
	@GetMapping("")
	public String hello() {
		return "Hello Spring Boot World";
	}
	
	@GetMapping(HELLO)
	// @RequestParam(name="", value="", required=true, defaultValue="")
	// URL로 데이터를 받는 경우(Get, Delete) 쿼리 형태로 데이터를 받음
	// http://호스트:포트/end-point?name=value&...
//	public String getHello(@RequestParam("name") String name) {
//		return "This is Get Method, end-point '/hello'" + name;
//	}
	public String getHello(@RequestParam(name="name", required=false, defaultValue="Donguk") String name) {
		return "This is Get Method, end-point '/hello'" + name;
	}
	
	@GetMapping(HELLO + "/{name}/spring")
	// @PathVariable(path) : URL 데이터를 받는경우(Get, Delete) path 형태로 데이터를 받음
	// http://호스트:포트/end-point/VARIABLE
	public String getHelloName(@PathVariable("name") String name) {
		return "This is Get Method, end-point '/hello'" + name;
	}
	
	// @PostMapping(end-point) : 해당 end-point로 Post 방식의 Request가 왔을 때 동작
	@PostMapping(HELLO)
	// @RequestBody : 해당 Request의 Body에서 Json을 인식해 인스턴스로 변경 
	public HelloDTO postHello(@RequestBody HelloDTO requestBody) {
//		return "This is Post Method, end-point '/hello'";
		return requestBody;
	}
	
	// @PutMapping(end-point) : 해당 end-point로 Put 방식의 Request가 왔을 때 동작
	@PutMapping(HELLO)
	public String putHello() {
		return "This is Put Method, end-point '/hello'";
	}
	
	// @PatchMapping(end-point) : 해당 end-point로 Patch 방식의 Request가 왔을 때 동작
	@PatchMapping(HELLO)
	public String patchHello() {
		return "This is Patch Method, end-point '/hello'";
	}
	
	// @DeleteMapping(end-point) : 해당 end-point로 Delete 방식의 Request가 왔을 때 동작
	@DeleteMapping(HELLO)
	public String deleteHello() {
		return "This is Delete Method, end-point '/hello'";
	}
	
	
}
