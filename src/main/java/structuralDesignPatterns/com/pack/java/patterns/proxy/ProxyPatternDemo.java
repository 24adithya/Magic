package com.pack.java.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPatternDemo {

	public static void main(String[] args)
	{
		TwitterService service = (TwitterService) SecurityProxy.newInstance(TwitterStub.class);
//		TwitterService service = new TwitterStub();
 		System.out.println(service.getTimelinePosts("Adithya"));
	}
}

interface TwitterService
{
	String getTimelinePosts(String username);
	void postToTimeline(String post);
}

class TwitterStub implements TwitterService
{
	private TwitterStub() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getTimelinePosts(String username)
	{
		return "Adithya is an overall success !!";
	}

	@Override
	public void postToTimeline(String post)
	{
		
	}
}

class SecurityProxy implements InvocationHandler
{
	private Object object;
	
	public SecurityProxy(Object object) {
		this.object = object;
	}
	
	public static Object newInstance(Object object)
	{
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new SecurityProxy(object));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		return method.invoke(object, args);
	}
}