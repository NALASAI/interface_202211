package com.donguk.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.donguk.board.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

	// Query : 커스텀 ORM 메서드를 작성
	// 테이블명을 alias로 지정해서 사용
	// ?1, ?2, ... : 매개변수로 받아온 변수를 해당 위치로 넣기위한 구문
	@Query("select m from member m where m.email = ?1")
	public List<MemberEntity> myFindAll(String email);
	
	
}