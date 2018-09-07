package com.pack.java.dto.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class ArrayToObject {
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		String name = "Pranali";
		Integer id = 11;
		double sal = 10000.0;
		Object[] array = new Object[] {name, id, null, sal, 0 };
//		MyObject myObject = array.;
		
		//write object
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
		ObjectOutputStream out = new ObjectOutputStream(new DeflaterOutputStream(byteArrayOutputStream));
        out.writeObject(array);
        out.close();
        
        //read object
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = 
            new ObjectInputStream(new InflaterInputStream(byteArrayInputStream));
//        TODO MyObject myObject = (MyObject) objectInputStream.readObject();
        
//        MyObject myObject = new MyObject (array); 
        
        Class myObjClass = Class.forName("com.pack.java.dto.generator.MyObject");
        MyObject myObject = (MyObject) myObjClass.newInstance();
        Field[] fieldsArray = myObjClass.getDeclaredFields();
        Class fieldType = null;
        int count = 0;
        for(Field field : fieldsArray) {
        	System.out.println(field.getName());
        	
        	field.setAccessible(true);
        	field.set(myObject, array[count]);
        	fieldType = field.getType();
        	System.out.println(fieldType);
        	
        	if(count < array.length) {
        		count++;
        	}
        }
        
        System.out.println(myObject);
		
	}
}

class MyObject {
	private String name;
	private Integer id;
	private Integer dept = -1;
	private double sal;
	private int sooar;
	
	public MyObject() {
		// TODO Auto-generated constructor stub
	}
	
//	private Object[] params = new Object[] {name, id, sal, sooar};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	
//	public MyObject(Object... args) {
//		params = args;
//	}
//	
}
