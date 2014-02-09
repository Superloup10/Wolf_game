package fr.wolf.moteur;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Keybinding
{
	public static void input()
	{
		if(Mouse.isButtonDown(0))//Clic Gauche
		{
			int x = Mouse.getX();
			int y = Mouse.getY();
			
			System.out.println("Attaque");
		}
		if(Mouse.isButtonDown(1))//Clic Droit
		{
			int x = Mouse.getX();
			int y = Mouse.getY();
			
			System.out.println("Parer");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			System.out.println("Sauter");
		}
			
		while(Keyboard.next())
		{
			if(Keyboard.getEventKeyState())
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_L)
				{
					System.out.println("Transformer");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_S)
				{
					System.out.println("Descendre");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_D)
				{
					System.out.println("Avancer");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_Q)
				{
					System.out.println("Reculer");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					System.out.println("Pause");
				}
			}
		}
	}
}