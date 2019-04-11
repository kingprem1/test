package com.example.demo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	@Id
	private String id;
	
	private String userName;
	private String passWord;
	
	public User(){
		
	}
	
	public User(String _userName, String _passWord){
		//setId(_id);
		setUserName(_userName);
		setPassWord(_passWord);
	}
	
	public void setId(String _id) {
		id = _id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUserName(String _userName) {
		userName = _userName;
	}
	
	public void setPassWord(String _passWord) {
		passWord = _passWord;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
}
