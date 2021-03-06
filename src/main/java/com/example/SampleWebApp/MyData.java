package com.example.SampleWebApp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mydata")
public class MyData {
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(nullable = true)
	private List<MsgData> msgdatas;
	
	public List<MsgData> getMsgdatas() {
		return msgdatas;
	}

	public void setMsgdatas(List<MsgData> msgdatas) {
		this.msgdatas = msgdatas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;
	
	@Column(length = 50, nullable = false)
	@NotEmpty
	private String name;
	
	@Column(length = 200, nullable = true)
	@Email
	private String mail;
	
	@Column(nullable = true)
	@Min(0)
	@Max(200)
	private int age;
	
	@Column(nullable = true)
	@Phone(onlyNumber = true)
	private String memo;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}

