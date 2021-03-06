package com.onyx.distruptor.test;

import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = 2035546038986494352L;
	private Long id ;
	private String name;
	
	public Data() {
	}
	public Data(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
        System.out.println("这是getID方法:"+id);
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Data{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
