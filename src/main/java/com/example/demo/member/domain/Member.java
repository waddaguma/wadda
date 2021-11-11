package com.example.demo.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 이 Annotation을 붙여주면 해당 변수를  PrimaryKey로 인식한다.
	private Long id;
	private String name;
	private Integer zipCode;
	private LocalDateTime eMail;
	private Boolean active;
	

}
