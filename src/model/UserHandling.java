package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserHandling {
	
	//establishing connection
			private Connection connect() { 
				
				Connection con = null; 
				try{ 
					Class.forName("com.mysql.jdbc.Driver"); 
			 
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", ""); 
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return con; 
			}
			
			//create user
			public String insertUser(String name, String pno, String email, String address, String accno) { 
				
				String output = ""; 
				
				try { 
					Connection con = connect(); 
					
					if (con == null) {
						return "Error while connecting to the database for inserting."; } 
						
						// create a prepared statement
						String query;
					
						query = " insert into userdb (`UID`,`name`,`phoneNO`,`email`,`address`,`accountNO`)" + " values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, name); 
						preparedStmt.setString(3, pno); 
						preparedStmt.setString(4, email); 
						preparedStmt.setString(5, address); 
						preparedStmt.setString(6, accno); 

						// execute the statement
						preparedStmt.execute(); 
						
						con.close(); 
						
						output = "Inserted successfully"; 
				} catch (Exception e) { 
					output = "Error while inserting the details."; 
					System.err.println(e.getMessage()); 
				}
				return output; 
			}  
		

			
			
			//read user details 
			public String readUser() { 
				
				String output = ""; 
				
				try { 
					Connection con = connect(); 
					
					if (con == null) {
						return "Error while connecting to the database for reading."; } 
			 
						// Prepare the html table to be displayed
						output = "<table border='1'><tr><th>User ID</th><th>Name</th><th>Phone NO</th><th>Email</th><th>Address</th><th>Account NO</th>"; 
						String query = "select * from userdb"; 
						java.sql.Statement stmt = con.createStatement(); 
						ResultSet rs = stmt.executeQuery(query); 
			 
						// iterate through the rows in the result set
			 
						while (rs.next()) { 
							String UID = Integer.toString(rs.getInt("UID")); 
							String name = rs.getString("name"); 
							String phoneNO = rs.getString("phoneNO"); 
							String email = rs.getString("email"); 
							String address = rs.getString("address"); 
							String accountNO = Integer.toString(rs.getInt("accountNO")); 
			
							
							// Add into the html table
							output += "<tr><td>" + UID + "</td>"; 
							output += "<td>" + name + "</td>"; 
							output += "<td>" + phoneNO + "</td>"; 
							output += "<td>" + address + "</td>";
							output += "<td>" + accountNO + "</td>"; 
							
							// buttons
							output += "<td><form method='post' action='#'>" 
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" 
					+ 
									"<input name='readUser' type='hidden' value='" + UID + "'>" + "</form></td></tr>"; 
						} 
			 
						con.close(); 
						// Complete the html table
						output += "</table>"; 
				} 
				catch (Exception e) { 
					output = "Error while reading the user."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
			}
			

			//delete user account
			public String deleteUser(String UID) {
				
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from userdb where UID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, Integer.parseInt(UID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					output = "User Deleted successfully";

				} catch (Exception e) {
					output = "Error while deleting the details.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			
			
					 
			
}
