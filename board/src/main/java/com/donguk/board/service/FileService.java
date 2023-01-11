package com.donguk.board.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Value("${file.dir}")
	private String dir;
	
	public String fileUpload(MultipartFile file) {
		// file의 유무 검사
		if(file.isEmpty()) return null;
		
		// Original file name 가져옴
		String originalFileName = file.getOriginalFilename();
		// 확장자 호출(.img, .png, ....)
		// 앞에서부터 찾기위해 IndexOf 사용시 중간에 . 이 있을수도 있어 뒤에서부터 호출해야 하기에 lastIndexOf 사용
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		// 저장할 때 이름으로 사용되는 UUID 생성
		String uuid = UUID.randomUUID().toString();
		// 실제로 저장되는 이름 생성
		String saveName = uuid + extension;
		// 파일의 저장된 실제 경로
		String savePath = dir + saveName; 
		
		// 해당 파일을 실제로 해당 경로에 저장
		try {
			file.transferTo(new File(savePath));
		}catch(Exception e) {
			return null;
		}
		
		return saveName;
	}
	
	// 파일 다운로드
	public Resource fileDownload(String fileName) {
		try {
			return new UrlResource("file:" + dir + fileName);
		}catch(Exception e) {
			return null;
		}
	}
	
	// 이미지 출력
	public Resource getImage(String imageName) {
		try {
			return new UrlResource("file:" + dir + imageName);
		}catch(Exception e) {
			return null;
		}
	}
}
