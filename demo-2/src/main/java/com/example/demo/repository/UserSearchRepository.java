package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.user.User;


@Repository
public class UserSearchRepository {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List searchUsers(String text) {
		return mongoTemplate.find(Query.query(new Criteria()
				.orOperator(Criteria.where("userName").regex(text,"i")))
				, User.class);
	}
	
	/*public List searchUsers() {
		return (List) mongoTemplate.getCollection("userList");
	}*/
}
