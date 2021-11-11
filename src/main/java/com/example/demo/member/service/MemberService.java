package com.example.demo.member.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.nmsmember.repository.NmsRepository;
import com.example.demo.test01.domain.Test01;
import com.example.demo.test01.service.Test01Save;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {


	@Autowired
	private MemberSave memberSave;

	@Autowired
	private Test01Save test01Save;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	NmsRepository nmsRepository ;

	@Value("${spring.datasource.oracle.jdbc-url}")
	private String JDBC_URL;

	@Value("${spring.datasource.oracle.username}")
	private String USER_NAME;

	@Value("${spring.datasource.oracle.driver-class-name}")
	private String CLASS_NAME;

	@Value("${spring.datasource.oracle.password}")
	private String PASSWORD;

	public void processMember() {

		//		List<Member> li = memberRepository.fetchByActive("f");
		List<Member> li = memberRepository.findAll();

		for(Member i : li) {

			try {
				memberSave.oneSave(i);
			}catch(Exception e) {
				//		log.debug(e.toString);
			}
		}

	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void deleteMember() {

		memberRepository.deleteMember();

	}

	//	public void memberStart(Member member) {
	//		
	//		memberRepository.truncateMember();
	//		
	//		memberRepository.save(member);
	//
	//	}

	public void memberStart(List<Member> member) {

		//		memberRepository.truncateMember();

		for(Member addMember : member) {
			System.out.println(addMember.getName());
			System.out.println(addMember.getId());

			memberRepository.save(addMember);


		}
	}


	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void processMemberOracle() {



		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//		 dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//		 dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/ORCL");
		//		 dataSource.setUsername("system");
		//		 dataSource.setPassword("Mook@2736");
		dataSource.setDriverClassName(CLASS_NAME);
		dataSource.setUrl(JDBC_URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);

		System.out.println("========: " + dataSource.getUsername());
		System.out.println("========: " + dataSource.getUrl());
		System.out.println("========: " + dataSource.getPassword());

		String query = "select * from src_member";

		try(Connection connection = dataSource.getConnection()){
			System.out.println(connection);
			String URL = connection.getMetaData().getURL();
			System.out.println(URL);
			String User = connection.getMetaData().getUserName();
			System.out.println(User);

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while(rs.next()){ 
				Test01 test01 = new Test01(); //tgt_test
				System.out.println("name : " + rs.getString("name"));
				System.out.println("id : " + rs.getString("id"));
				System.out.println("zipCode : " + rs.getString("zipCode"));


				//	                test01.setId(rs.getString("id"));
				test01.setEMail(rs.getString("eMail"));
				test01.setActive(rs.getString("active"));
				test01.setName(rs.getString("name"));
				test01.setZipCode(rs.getString("zipCode"));


				test01Save.oneSave(test01);

			}

		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}




	public void processMysql() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//	 dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//	 dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/ORCL");
		//	 dataSource.setUsername("system");
		//	 dataSource.setPassword("Mook@2736");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/?serverTimezone=UTC&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("Mook@2736");

		System.out.println("========: " + dataSource.getUsername());
		System.out.println("========: " + dataSource.getUrl());
		System.out.println("========: " + dataSource.getPassword());

		try(Connection connection = dataSource.getConnection()){
			System.out.println("============: " + connection);
			String URL = connection.getMetaData().getURL();
			System.out.println("============: " + URL);
			String User = connection.getMetaData().getUserName();
			System.out.println("============: " + User);


		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public void jasyptTest01() {
		
		System.out.println("CLASS_NAME :" + CLASS_NAME);
		System.out.println("JDBC_URL :" + JDBC_URL);
		System.out.println("USER_NAME :" + USER_NAME);
		System.out.println("PASSWORD :" + PASSWORD);

	}

}

