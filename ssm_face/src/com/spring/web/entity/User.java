package com.spring.web.entity;


public class User {
	private int id;
	private byte[] base64;// 列值 clob类型
	public User() {

	}

	public byte[] getBase64() {
		return base64;
	}

	public void setBase64(byte[] base64) {
		this.base64 = base64;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
