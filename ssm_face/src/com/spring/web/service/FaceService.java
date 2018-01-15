package com.spring.web.service;

import java.util.List;

import com.spring.web.entity.User;

public interface FaceService {
	/** 查询所有的人脸信息 */
	public List<User> selectAllUsers();

}
