package com.example.demo.test01.domain;

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
@Table(name="tgt_test")
public class Test01 {

	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String active;
	private String eMail;
	private String name;
	private String zipCode;
}
