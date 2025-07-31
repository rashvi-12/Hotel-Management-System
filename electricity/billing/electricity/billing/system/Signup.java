package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Signup extends JFrame implements ActionListener
{
	JButton create,back;
	Choice accounttype;
	JTextField meter,username,name,password;
	Signup()
	{
		setSize(700,400);
		setLocation(450,150);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel=new JPanel();
		panel.setBounds(30,30,650,300);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Amount",TitledBorder.LEADING ,TitledBorder.TOP,null, (Color.RED )));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setForeground(new Color(34,139,34));
		add(panel);
		
		JLabel heading=new JLabel("Create Amount");
		heading.setBounds(100,50,140,20);
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(heading);
		
		accounttype=new Choice();
		accounttype.add("Admin");
		accounttype.add("Customer");
		accounttype.setBounds(260,50,150,20);
		panel.add(accounttype);
		
		JLabel meternumber=new JLabel("Meter Number");
		meternumber.setBounds(100,90,140,20);
		meternumber.setForeground(Color.GRAY);
		meternumber.setFont(new Font("Tahoma",Font.BOLD,14));
		meternumber.setVisible(false);
		panel.add(meternumber);
		
		 meter =new JTextField();
		meter.setBounds(260,90,150,20);
		meter.setVisible(false);
		panel.add(meter);
		
		
		
		JLabel lblusername =new JLabel("Username");
		lblusername.setBounds(100,130,140,20);
		lblusername.setForeground(Color.GRAY);
		lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblusername);
		
		 username =new JTextField();
		username.setBounds(260,130,150,20);
		panel.add(username);
		
		meter.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {}
			@Override
			public void focusLost(FocusEvent fe)  {
				try {
					Conn c =new Conn();
					ResultSet rs=c.s.executeQuery("select * from login4 where meter_no='"+meter.getText()+"'");
					while(rs.next())
					{
						name.setText(rs.getString("name"));
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
		
		JLabel lblname =new JLabel("Name");
		lblname.setBounds(100,170,140,20);
		lblname.setForeground(Color.GRAY);
		lblname.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblname);
		
		name=new JTextField();
		name.setBounds(260,170,150,20);
		panel.add(name);
		
		JLabel lblpassword =new JLabel("Password");
		lblpassword.setBounds(100,210,140,20);
		lblpassword.setForeground(Color.GRAY);
		lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblpassword);
		
		password =new JTextField();
		password.setBounds(260,210,150,20);
		panel.add(password);
		
		accounttype.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				String user=accounttype.getSelectedItem();
				if(user.equals("Customer"))
				{
					meternumber.setVisible(true);
					meter.setVisible(true);
					name.setVisible(false);
				}else {
					meternumber.setVisible(false);
					meter.setVisible(false);
					name.setVisible(true);
				}
			}
			
			
			
		});
		
		create = new JButton("Create");
		create.setBackground(Color.BLACK);
		create.setForeground(Color.WHITE);
		create.setBounds(140,260,120,25);
		create.addActionListener(this);
		panel.add(create);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(300,260,120,25);
		back.addActionListener(this);
		panel.add(back);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
		Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(415,30,250,250);
        panel.add(image);
		
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==create)
		{
			String atype=accounttype.getSelectedItem();
			String susername=username.getText();
			String sname=name.getText();
			String spassword=password.getText();
			String smeter=meter.getText();
			
			try {
				Conn c=new Conn();
				String query=null;
				if(atype.equals("Admin")) {
					query ="insert into login4 values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
				}else
				{
					query="update login4 set username = '"+susername+"' , password='"+spassword+"',user='"+atype+"' where meter_no= '"+smeter+"'";
				}
				
				c.s.executeUpdate(query);
				
				 JOptionPane.showMessageDialog(null,"Account Create Successfully");
					
					setVisible(false);
					new Login();
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(ae.getSource()==back)
		{
			setVisible(false);
			new Login();
		}
	}

	public static void main(String[] args) {
	new Signup();

	}

}
