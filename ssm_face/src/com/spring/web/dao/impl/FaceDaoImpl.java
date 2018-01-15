package com.spring.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.spring.web.dao.FaceDao;
import com.spring.web.entity.User;
import com.spring.web.service.FaceService;

@Repository
public class FaceDaoImpl implements FaceDao {

	@Resource
	private SqlSessionFactory sqlSessionFactory;

	public List<User> selectAllUsers() {
		SqlSession sqlSession = sqlSessionFactory.openSession(); // 构建SqlSession
		List<User> user = null;
		user = sqlSession.selectList(User.class.getName() + ".selectAllUsers");

		for (User users : user) {
			String t = new String(users.getBase64());//
			System.out.println(t);
		}

		return user;

	}

	/** 测试查询全部的学生 */
	@Test
	public void testSelectAll() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		FaceService dao = (FaceService) ac.getBean("faceServiceImpl");

		dao.selectAllUsers();
	}

	/** 测试查询全部的学生 */
	@Test
	public void testSelectAlls() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		FaceService dao = (FaceService) ac.getBean("faceServiceImpl");
		List<User> users = dao.selectAllUsers();
	}

}
