package com.example.demo.test01.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.test01.domain.Test01;

//public interface Test01Repository extends JpaRepository<Test01,Long>, QuerydslPredicateExecutor<Test01> {

@Mapper
@Repository
//public interface Test01Repository extends JpaRepository<Test01,Long>, QuerydslPredicateExecutor<Test01> {
	public interface Test01Repository {
//	@Modifying(flushAutomatically = true)
//	@Query(value="delete from tgt_test", nativeQuery=true)
//	void deleteTgt();
//	
//	@Modifying
//	@Query(value="truncate table  tgt_test", nativeQuery=true)
//	void truncateTgt();
	
	void insertTgt(Test01 test01);
	
	void deleteTgt();
}
