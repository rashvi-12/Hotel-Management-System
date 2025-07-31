package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class Splash extends JFrame implements Runnable{
		Thread t;
		Splash()
		{
	        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
			Image i2=i1.getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT);
			ImageIcon i3=new ImageIcon(i2);
			JLabel image =new JLabel(i3);
			add(image);
			
			setVisible(true);
			int x=1;
			for(int i=1;i<450;i+=2,i+=1) 
			{
				setSize(i+x,i);
				setLocation(750-(i+x),500-i);
				try {
					Thread.sleep(3);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
			t=new Thread(this);
			t.start();
		
			setVisible(true);
			
			

		}
		public void run()
		{
			try {
				Thread.sleep(5000);
				setVisible(false);
				
				//login Frame
				new Login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	   public static void main(String[] args) 
	   {
		   new Splash();
	   }

	}