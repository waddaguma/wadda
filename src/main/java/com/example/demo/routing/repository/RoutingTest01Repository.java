package com.example.demo.routing.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoutingTest01Repository {
	
	String selectTest01();

}
