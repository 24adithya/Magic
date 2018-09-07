package com.pack.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class TestAssignment implements MouseListener {

	public static void main(String[] args)
	{
		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Listen");
			}
			
			/*@Override
			public void listen()
			{
				System.out.println("Listen");
			}*/
		};
		
		add(ActionListener.class, l);
	}
	public static <T extends ActionListener> void add(Class<T> t, T l) {
		if (!t.isInstance(l)) {
			System.out.println("aahe");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}

interface Listener{
	void listen();
}

