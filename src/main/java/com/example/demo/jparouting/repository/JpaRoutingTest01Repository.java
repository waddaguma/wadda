package com.example.demo.jparouting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.jparouting.domain.PcmTest01;

@Repository
public interface JpaRoutingTest01Repository extends JpaRepository<PcmTest01, String>, QuerydslPredicateExecutor<PcmTest01>{

}
