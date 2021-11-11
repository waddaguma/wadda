package com.example.demo.test01.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.JasyptConfig;
import com.example.demo.test01.domain.Test01;
import com.example.demo.test01.repository.Test01Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class Test01Service {


	@Autowired
	private Test01Service test01Service;

	@Autowired
	private Test01Save test01Save;

	@Autowired
	private Test01Repository test01Repository;

	@Autowired
	private JasyptConfig jasyptConfig;


	@Value("${spring.datasource.oracle.jdbc-url}")
	private String JDBC_URL;

	@Value("${spring.datasource.oracle.username}")
	private String USER_NAME;

	@Value("${spring.datasource.oracle.driver-class-name}")
	private String CLASS_NAME;

	@Value("${spring.datasource.oracle.password}")
	private String PASSWORD;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void truncateTgt() {
//		test01Repository.truncateTgt();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteTgt() {
		test01Repository.deleteTgt();
		
	}

	public void processMemberOracle2() {
		deleteTgt();
		//			test01Repository.deleteTgt();
		//			test01Repository.deleteAll();
		//			
//					test01Repository.flush();
		processMemberOracle();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteTest01() {
		test01Repository.deleteTgt();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void processMemberOracle() {

		//            test01Repository.deleteTgt();
		//            test01Repository.flush();

		//			test01Repository.deleteAll();
		//			test01Repository.flush();
		//			test01Repository.truncateTgt();
		//			StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor(); 
		//			pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		////			pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		//			pbeEnc.setPassword("111");



		//			System.out.println("CLASS_NAME = " + pbeEnc.encrypt("oracle.jdbc.driver.OracleDriver"));
		//			System.out.println("JDBC_URL = " + pbeEnc.encrypt("jdbc:oracle:thin:@localhost:1521/ORCL"));
		//			System.out.println("USER_NAME = " + pbeEnc.encrypt("system"));
		//			System.out.println("PASSWORD = " + pbeEnc.encrypt("Mook@2736"));


		//			System.out.println("CLASS_NAME = " +pbeEnc.decrypt(CLASS_NAME));
		//			System.out.println("JDBC_URL = " + pbeEnc.decrypt(JDBC_URL));
		//			System.out.println("USER_NAME = " + pbeEnc.decrypt(USER_NAME));
		//			System.out.println("PASSWORD = " + pbeEnc.decrypt(PASSWORD));

		//			System.out.println("CLASS_NAME = " + CLASS_NAME);
		//			System.out.println("JDBC_URL = " + JDBC_URL);
		//			System.out.println("USER_NAME = " + USER_NAME);
		//			System.out.println("PASSWORD = " + PASSWORD);




		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(CLASS_NAME);
		dataSource.setUrl(JDBC_URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);

		String query = "select * from src_member";

		try(Connection connection = dataSource.getConnection()){

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);



			while(rs.next()){ 
				Test01 test01 = new Test01();
				System.out.println("name : " + rs.getString("name"));
				System.out.println("id : " + rs.getString("id"));
				System.out.println("zipCode : " + rs.getString("zip_code"));	                

				test01.setId(rs.getLong("id"));
				test01.setEMail(rs.getString("e_mail"));
				test01.setActive(rs.getString("active"));
				test01.setName(rs.getString("name"));
				test01.setZipCode(rs.getString("zip_code"));


				test01Save.oneSave(test01);
//				test01Repository.insertTgt(test01);

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

