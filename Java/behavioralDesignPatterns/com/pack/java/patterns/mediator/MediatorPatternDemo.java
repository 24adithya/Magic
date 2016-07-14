package com.pack.java.patterns.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorPatternDemo {

	public static void main(String[] args)
	{
		Light hallLight = new Light("Hall");
		Light kitchenLight = new Light("Kitchen");
		
		Mediator mediator = new Mediator();
		mediator.registerLight(kitchenLight);
		mediator.registerLight(hallLight);
		
		Command command = new ToggleCommand(mediator);
		
		Switch invoker = new Switch();
		
		invoker.storeAndExecute(command);
		invoker.storeAndExecute(command);
		invoker.storeAndExecute(command);
		
		System.out.println("-------------- All Lights --------------");
		
		//One command tied to an array of lights for toggling purpose
		List<Light> lights = new ArrayList<>();
//		lights.add(kitchenLight);
		lights.add(hallLight);
		
		Command allLightsCommand = new AllLightsToggleCommand(mediator);
		
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
	//Tie the mediator to this command
	private Mediator mediator;
	
	public ToggleCommand(Mediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute()
	{
		mediator.toggle();
	}
}

//mediator
class Mediator
{
	private List<Light> lights;
	
	public Mediator() {
		lights = new ArrayList<>();
	}
	
	public void registerLight(Light light)
	{
		lights.add(light);
	}
	
	public void toggle()
	{
		for (Light light : lights)
		{
			if(light.isOn())
			{
				light.switchOff();
				light.setOn(false);
			}
			else
			{
				light.switchOn();
				light.setOn(true);
			}
		}
	}
}

//concrete command
class AllLightsToggleCommand implements Command
{
	//Tie the receiver (light) to this command
	private Mediator mediator;
	
	public AllLightsToggleCommand(Mediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute()
	{
		mediator.toggle();
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

	public void setOn(boolean isOn)
	{
		this.isOn = isOn;
	}
	
	public void switchOff()
	{
		System.out.println("" + getName() + " light switched off");
	}
	
	public void switchOn()
	{
		System.out.println("" + getName() + " light switched on");
	}
}

//invoker
class Switch
{
	public Switch() {
	}
	
	public void storeAndExecute(Command command)
	{
		command.execute();
	}
}
