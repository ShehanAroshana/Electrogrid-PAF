package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class Complain {
	
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		// Connect to the DB
		public Connection connect()
		{
			Connection con = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
				
				//for testing
				System.out.print("Succesfully connected to the DB");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		//insert a Complain
		public String insertComplain(String desc, String comDate)
		{
			String output = "";
			
			try 
			{
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting.";}
				
				//create a prepared statement 
				String query = " insert into complain_table (`Complain_id`,`Description`,`complainDate`)"
						+ " values (?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, desc);
				preparedStmt.setString(3, comDate);
				
				
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Successfully Inserted ";
				
			}catch(Exception e)
			{
				output = "Error while inserting the Complains ."; 
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		//Read a Complain
				public String readComplain() 
				{
					String output = "";
					
					try
					{
						Connection con = connect();
						
						if(con == null)
						{
							return "Error while connecting to the database for Reading.";}
						
						//Prepare the html table to be displayed
						output = "<table border='1'><tr><th>Complain ID</th>" 
						        +  "<th>Complain</th>"
								+  "<th>Date</th></tr>";
						
						String query = "select * from complain_table";
						 java.sql.Statement stmt = con.createStatement(); 
						 ResultSet rs = stmt.executeQuery(query); 
						
						// iterate through the rows in the result set
						while(rs.next())
						{
							 String comId = Integer.toString(rs.getInt("Complain_id")); 
							 String comDec = rs.getString("Description"); 
							 String comDate = rs.getString("complainDate"); 
						
						
						 // Add into the html table
						 output += "<tr><td>" + comId + "</td>"; 
						 output += "<td>" + comDec + "</td>"; 
						 output += "<td>" + comDate + "</td>"; 
					
						 // buttons
						output += "<td><form method='post' action='#'>"
								+ "<input name='btnUpdate' "
								+ " type='submit' value='Update'>"
								+ "<td><form method='post' action='#'>"
								+ "<input name='btnRemove' "
								+ " type='submit' value='Remove'>"
								+ "<input name='comId' type='hidden' "
							    + " value='" + comId + "'>" + "</form></td></tr>"; 
						 		
						
						}
						 con.close();
						 // Complete the html table
						 output += "</table>"; 
					}
					catch(Exception e)
					{
						output = "Error while Reading the Complains ."; 
						System.err.println(e.getMessage());
					}
					return output;
					
				}
				
				
				//Update Complain		
				public String updateComplain(String desc, String ComplainId)
				{
					String output = "";
					
					try
					{
						Connection con = connect();
						
						if(con == null)
						{return "Error while connecting to the database for Updating.";}
						
						//create a prepared statement
						String query = "UPDATE complain_table SET Description=? WHERE Complain_id =?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//Binding Values
						preparedStmt.setString(1, desc);
						preparedStmt.setInt(2, Integer.parseInt(ComplainId)); 
						
						
						// execute the statement
						 preparedStmt.execute(); 
						 con.close(); 
						 output = "Updated successfully";
						
					}catch(Exception e)
					{
						output = "Error while Updating the Complains ."; 
						System.err.println(e.getMessage());
					}
					return output;
					
					
				}
				
				//Delete a Complain
				public String deleteComplain(String comID) 
				{
					String output = "";
					
					try 
					{
						Connection con = connect();
						
						if(con == null) 
						{return "Error while connecting to the database for deleting.";}
						
						//create a prepared statement
						String query = "delete from complain_table where Complain_id=?";
					
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//binding values
						preparedStmt.setInt(1, Integer.parseInt(comID));
						
						
						//execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Complain details Deleted successfully";
						
					}
					catch(Exception e) {
						output = "Error while deleting the Complain details.";
						System.err.println(e.getMessage());
					}
					
					return output;
				}
				
		
}
