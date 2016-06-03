package com.pack.java.patterns.chainofresponsibility;

public class ChainOfResponsibilityDemo {

	public static void main(String[] args)
	{
		Handler bryan = new Director();
		Handler crystal = new VP();
		Handler adithya = new CEO();
		
		bryan.setSuccessor(crystal);
		crystal.setSuccessor(adithya);
		
		Request request = new Request(RequestType.CONFERENCE, 500);
		bryan.handleRequest(request);
		
		request = new Request(RequestType.PURCHASE, 1000);
		bryan.handleRequest(request);
		
		request = new Request(RequestType.PURCHASE, 2000);
		bryan.handleRequest(request);
	}
}

abstract class Handler
{
	protected Handler successor;
	public abstract void handleRequest(Request request);
	
	public void setSuccessor(Handler successor)
	{
		this.successor = successor;
	}
}

class Director extends Handler
{

	@Override
	public void handleRequest(Request request)
	{
		if(RequestType.CONFERENCE == request.getRequestType())
		{
			System.out.println("Directors can approve conferences.");
		}
		else
		{
			successor.handleRequest(request);
		}
	}
}

class CEO extends Handler
{
	@Override
	public void handleRequest(Request request)
	{
		System.out.println("CEOs can approve anything they want.");
	}
}

class VP extends Handler
{
	@Override
	public void handleRequest(Request request)
	{
		if(RequestType.PURCHASE == request.getRequestType() && request.getAmount() < 1500)
		{
			System.out.println("VPs can approve purchases below 1500.");
		}
		else
		{
			successor.handleRequest(request);
		}
	}
}

enum RequestType{
	CONFERENCE, PURCHASE;
}

class Request
{
	private RequestType requestType;
	private double amount;
	
	public Request(RequestType requestType, double amount) {
		this.requestType = requestType;
		this.amount = amount;
	}
	
	public RequestType getRequestType()
	{
		return requestType;
	}
	public void setRequestType(RequestType requestType)
	{
		this.requestType = requestType;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}