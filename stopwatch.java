import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Stopwatch extends Thread
{		
		
		static JLabel hours = new JLabel("00:");
		static JLabel minutes = new JLabel("00:");
		static JLabel seconds = new JLabel("00:");
		static JLabel miliseconds = new JLabel("00");
		static JButton start = new JButton("START");
		static JButton stop = new JButton("STOP");
		static int mili=0;
		static int sec=0;
		static int min=0;
		static int hour=0;
		static int state=1;
		static JButton reset = new JButton("RESET");
		
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		
		
		frame.setVisible(true);
		frame.setBounds(500,100,275,225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cntr = frame.getContentPane();
		cntr.setLayout(null);
		
		cntr.add(hours);
		cntr.add(minutes);
		cntr.add(seconds);
		cntr.add(miliseconds);
		cntr.add(start);
		cntr.add(stop);
		cntr.add(reset);
		
		Font f = new Font("arial",Font.BOLD,30);
		hours.setFont(f);
		minutes.setFont(f);
		seconds.setFont(f);
		miliseconds.setFont(f);
		
		
		hours.setBounds(25,10,50,50);
		minutes.setBounds(80,10,50,50);
		seconds.setBounds(135,10,50,50);
		miliseconds.setBounds(190,10,250,50);
		start.setBounds(25,75,100,30);
		stop.setBounds(130,75,100,30);
		reset.setBounds(75,125,100,30);
		
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				state=1;
				
				Thread t = new Thread()
				{
					public void run()
					{
						
							for(;;)
							{
								if(state==1)
								{
								
									try
									{
										sleep(1);
									
										if(mili>1000)
										{
											mili=0;
											sec++;
										}
									
										if(sec>60)
										{
											mili=0;
											sec=0;
											min++;
										}
									
										if(min>60)
										{
											mili=0;
											sec=0;
											min=0;
											hour=0;
										}
										
										miliseconds.setText(":"+mili);
										mili++;
										seconds.setText(":"+sec);
										minutes.setText(":"+min);
										hours.setText(""+hour);
									}
					
									catch(Exception e)
									{
								
									}
								}
								
								else if(state==2)
								{
									break;
								}
								
								else if(state==3)
								{
									miliseconds.setText(": 0");
									seconds.setText(": 0");
									minutes.setText(": 0");
									hours.setText("0");
									
									mili=0;
									sec=0;
									min=0;
									hour=0;
									
								}
							}	
					}
				};
				t.start();
			}
		});
		
		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				state=2;
			}
		});
		
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				state=3;
				miliseconds.setText(": 0");
				seconds.setText(": 0");
				minutes.setText(": 0");
				hours.setText("0");
				mili=0;
				sec=0;
				min=0;
				hour=0;
				
			}
		});
	}		
}