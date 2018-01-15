package com.spring.web.dao;

import java.util.List;

import com.spring.web.entity.User;

public interface FaceDao {
	/** 查询人脸的信息 */
	public List<User> selectAllUsers();

}
