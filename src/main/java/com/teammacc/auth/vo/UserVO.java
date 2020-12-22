package com.teammacc.auth.vo;

import java.io.Serializable;

import javax.xml.catalog.Catalog;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.teammacc.auth.entity.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"userName","password"})
public class UserVO extends RepresentationModel<UserVO> implements Serializable  {

	private static final long serialVersionUID = -8387927312441830960L;

	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("password")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static UserVO create(User user) {
		return new ModelMapper().map(user, UserVO.class);		
	}
	
	

}
