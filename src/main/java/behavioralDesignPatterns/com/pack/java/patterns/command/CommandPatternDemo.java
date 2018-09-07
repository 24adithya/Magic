package com.pack.java.patterns.command;

import java.util.ArrayList;
import java.util.List;

public class CommandPatternDemo {

	public static void main(String[] args)
	{
		Light hallLight = new Light("Hall");
		Light kitchenLight = new Light("Kitchen");
		
		Command command = new ToggleCommand(hallLight);
		
		Switch invoker = new Switch();
		
		invoker.storeAndExecute(command);
		invoker.storeAndExecute(command);
		invoker.storeAndExecute(command);
		
		System.out.println("-------------- All Lights --------------");
		
		//One command tied to an array of lights for toggling purpose
		List<Light> lights = new ArrayList<>();
//		lights.add(kitchenLight);
		lights.add(hallLight);
		
		Command allLightsCommand = new AllLightsToggleCommand(lights);
		
		invoker.storeAndExecute(allLightsCommand);
		invoker.storeAndExecute(allLightsCommand);
		invoker.storeAndExecute(allLightsCommand);
	}
}

//command
interface Command
{
	void execute();
}

//Concrete command
class ToggleCommand implements Command
{
	//Tie the receiver (light) to this command
	private Light light;
	
	public ToggleCommand(Light light) {
		this.light = light;
	}
	
	@Override
	public void execute()
	{
		light.toggle();
	}
}

//concrete command
class AllLightsToggleCommand implements Command
{
	//Tie the receiver (light) to this command
	private List<Light> lights;
	
	public AllLightsToggleCommand(List<Light> lights) {
		this.lights = lights;
	}
	
	@Override
	public void execute()
	{
		for (Light light : lights)
		{
			light.toggle();
		}
	}
}

//receiver
class Light
{
	
	public Light() {
		
	}
	
	public Light(String name) {
		this.name = name;
	}
	
	private String name;
	private boolean isOn;
	
	public void toggle()
	{
		if(isOn)
		{
			switchOff();
			isOn = false;
		}
		else
		{
			switchOn();
			isOn = true;
		}
	}
	
	private void switchOff()
	{
		System.out.println("" + getName() + " light switched off");
	}
	
	private void switchOn()
	{
		System.out.println("" + getName() + " light switched on");
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isOn()
	{
		return isOn;
	}
}

//invoker
class Switch
{
	public Switch() {
		// TODO Auto-generated constructor stub
	}
	
	public void storeAndExecute(Command command)
	{
		command.execute();
	}
}
