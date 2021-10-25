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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.nmsmember.repository.NmsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {


	@Autowired
	private MemberSave memberSave;

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
		 
		 String query = "select * from member";

	        try(Connection connection = dataSource.getConnection()){
	            System.out.println(connection);
	            String URL = connection.getMetaData().getURL();
	            System.out.println(URL);
	            String User = connection.getMetaData().getUserName();
	            System.out.println(User);
	            
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(query);
	            
	            while(rs.next()){ 
	        		Member member = new Member();
	                System.out.println("name : " + rs.getString("name"));
	                System.out.println("id : " + rs.getString("id"));
	                System.out.println("zipcode : " + rs.getString("zipcode"));
	                
	                
	                member.setId(rs.getLong("id"));
	                member.setEmail(rs.getString("email"));
	                member.setActive(rs.getString("active"));
	                member.setName(rs.getString("name"));
	                member.setZipcode(rs.getString("zipcode"));
	                
	            	
	            	memberSave.oneSave(member);
	            	
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

  
       
	}

