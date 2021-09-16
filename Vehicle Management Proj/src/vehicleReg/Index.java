package vehicleReg;

import java.awt.EventQueue;
//import java.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Index {

	private JFrame frame;
	private JTextField txtvname;
	private JTextField textvehicleno;
	private JTextField textvyear;
	private JTextField textmobile;
	private JTable table;
	private JTextField textvid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sample321", "root", "@Vishnu97");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	
	 public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from vehicle");
		    rs = pst.executeQuery();
		   table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vehicle Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(266, 11, 252, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 87, 384, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Vehicle Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 44, 144, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vehicle No");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(22, 92, 125, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Manufactured Year");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 135, 182, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mobile No");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(22, 180, 111, 32);
		panel.add(lblNewLabel_1_3);
		
		txtvname = new JTextField();
		txtvname.setBounds(190, 53, 144, 20);
		panel.add(txtvname);
		txtvname.setColumns(10);
		
		textvehicleno = new JTextField();
		textvehicleno.setColumns(10);
		textvehicleno.setBounds(190, 101, 144, 20);
		panel.add(textvehicleno);
		
		textvyear = new JTextField();
		textvyear.setColumns(10);
		textvyear.setBounds(190, 144, 144, 20);
		panel.add(textvyear);
		
		textmobile = new JTextField();
		textmobile.setColumns(10);
		textmobile.setBounds(190, 189, 144, 20);
		panel.add(textmobile);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String vname,vno,myr,mobno;
				
				vname=txtvname.getText();
				vno=textvehicleno.getText();
				myr=textvyear.getText();
			    mobno=textmobile.getText();
			    
			    try {
					pst = con.prepareStatement("insert into vehicle(vehiclename,vehicleno,manufactureyr,mobileno )values(?,?,?,?)");
					pst.setString(1, vname);
					pst.setString(2, vno);
					pst.setString(3, myr);
					pst.setString(4, mobno);
					pst.executeUpdate();
				//	JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtvname.setText("");
					textvehicleno.setText("");
					textvyear.setText("");
					textmobile.setText("");
					txtvname.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(10, 350, 109, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(139, 350, 120, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtvname.setText("");
				textvehicleno.setText("");
				textvyear.setText("");
				textmobile.setText("");
				txtvname.requestFocus();
			
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(282, 350, 109, 41);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(395, 87, 424, 304);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 431, 384, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Vehicle ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 33, 96, 39);
		panel_1.add(lblNewLabel_2);
		
		textvid = new JTextField();
		textvid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				 try {
			          
			            String id = textvid.getText();

			                pst = con.prepareStatement("select vehiclename,vehicleno,manufactureyr,mobileno  from vehicle where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String vehiclename = rs.getString(1);
			                
			                String vehicleno = rs.getString(2);
			                String manufactureyr = rs.getString(3);
			                String mobileno = rs.getString(4);
			                
			                txtvname.setText(vehiclename);
							textvehicleno.setText(vehicleno);
							textvyear.setText(manufactureyr);
							textmobile.setText(mobileno);
			                
			                
			            }   
			            else
			            {
			            	txtvname.setText("");
							textvehicleno.setText("");
							textvyear.setText("");
							textmobile.setText("");
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
			}
		});
		textvid.setBounds(144, 44, 138, 20);
		panel_1.add(textvid);
		textvid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
String vname,vno,myr,mobno,vid;


				vname=txtvname.getText();
				vno=textvehicleno.getText();
				myr=textvyear.getText();
			    mobno=textmobile.getText();
			    vid  = textvid.getText();
				
				 try {
						pst = con.prepareStatement("update vehicle set vehiclename= ?,vehicleno=?,manufactureyr=?,mobileno=? where id =?");
						pst.setString(1, vname);
						pst.setString(2, vno);
			            pst.setString(3, myr);
			            pst.setString(4, mobno);
			            pst.setString(5, vid);
			            pst.executeUpdate();
			           //JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtvname.setText("");
						textvehicleno.setText("");
						textvyear.setText("");
						textmobile.setText("");
						txtvname.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(458, 400, 137, 41);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
	             String vid;
	 			vid  = textvid.getText();
	 			
	 			 try {
	 					pst = con.prepareStatement("delete from vehicle where id =?");
	 			
	 		            pst.setString(1, vid);
	 		            pst.executeUpdate();
	 		    //        JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
	 		            table_load();
	 		           
	 		           txtvname.setText("");
						textvehicleno.setText("");
						textvyear.setText("");
						textmobile.setText("");
						txtvname.requestFocus();
	 				}
	  
	 	            catch (SQLException e1) {
	 					
	 					e1.printStackTrace();
	 				}
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(611, 400, 137, 41);
		frame.getContentPane().add(btnNewButton_4);
	}
}
