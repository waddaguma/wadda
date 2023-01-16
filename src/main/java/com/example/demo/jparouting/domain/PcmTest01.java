package com.example.demo.jparouting.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Entity(name="pcm_test01")
public class PcmTest01 {
	
	@Id
	private String f01;
	private String f02;

}
