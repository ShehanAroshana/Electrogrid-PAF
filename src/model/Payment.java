package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	// Connect to the DB
		public Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paymentdatabase", "root", "");

				// For testing
				System.out.print("Succesfully connected to the DB");
				
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			return con;

		}

		         //Insert Buyers
				public String insertPayment(String username, String email, String address, String connumber, String cname, String cardno, String expdate, String cvv, String paymentdate,String amount)
				{ 
					Connection con = connect();
					String output = "";
					try
					 { 
						  
						 if (con == null) 
						 { 
						    return "Error while connecting to the database"; 
						 } 
						 
						 // create a prepared statement
						 String query = " insert into payment (`PaymentID`,`Name`,`Email`,`Address`,`ContactNumber`,`CardName`,`CreditCardNumber`,`ExpiryDate`,`CVV`,`PaymentDate`,`Amount`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
						 
						 PreparedStatement Pstatement = con.prepareStatement(query); 
						 
						 // binding values
						 Pstatement.setInt(1, 0); 
						 Pstatement.setString(2, username); 
						 Pstatement.setString(3, email); 
						 Pstatement.setString(4, address); 
						 Pstatement.setString(5, connumber);
						 Pstatement.setString(6, cname);
						 Pstatement.setString(7, cardno);
						 Pstatement.setString(8, expdate);
						 Pstatement.setString(9, cvv);
						 Pstatement.setString(10, paymentdate);
						 Pstatement.setString(11, amount);
						 
						 
						//execute the statement
						 
						 Pstatement.execute(); 
						 con.close();
						 System.out.println(query);
						 
						 output = "Payment Inserted successfully"; 
					 } 
					
					catch (Exception e) 
					 { 
						 output = "Error while inserting"; 
						 
						System.err.println(e.getMessage()); 
					 } 
					//binding values
					return output; 
				}
				public String readPayment()
				{ 
					 String output = ""; 
					 
					 try
					 { 
					
				     Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for reading."; 
					 } 
					 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'>"
					 		 + "<tr><th>Payment ID</th>" 
							 + "<th>Name</th>"
							 + "<th>Email</th>"
							 + "<th>Address</th>"
							 + "<th>Contact Number</th>" 
							 + "<th>Card Name</th>"
							 + "<th>Credit Card Number</th>"
							 + "<th>Expiry Date</th>"
							 + "<th>CVV</th>"
							 + "<th>Payment Date</th>"
							 + "<th>Amount</th>"
							 + "<th>Update</th><th>Remove</th></tr>"; 
					 
					 String query = "select * from payment"; 
					 
					 Statement stmt = (Statement) con.createStatement(); 
					 ResultSet res = ((java.sql.Statement) stmt).executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (res.next()) 
					 { 
						 String PaymentID = Integer.toString(res.getInt("PaymentID")); 
						 String Name = res.getString("Name"); 
						 String Email = res.getString("Email"); 
						 String Address = res.getString("Address"); 
						 String ContactNumber = res.getString("ContactNumber"); 
						 String CardName = res.getString("CardName"); 
						 String CreditCardNumber = res.getString("CreditCardNumber"); 
						 String ExpiryDate = res.getString("ExpiryDate"); 
						 String CVV = res.getString("CVV");
						 String PaymentDate = res.getString("PaymentDate");
						 String Amount = res.getString("Amount");
						 
						 // Add a row into the html table
						 output += "<tr><td>" + PaymentID + "</td>";
						 output += "<td>" + Name + "</td>";
						 output += "<td>" + Email + "</td>"; 
						 output += "<td>" + Address + "</td>";
						 output += "<td>" + ContactNumber + "</td>";
						 output += "<td>" + CardName + "</td>"; 
						 output += "<td>" + CreditCardNumber + "</td>"; 
						 output += "<td>" + ExpiryDate + "</td>";
						 output += "<td>" + CVV + "</td>";
						 output += "<td>" + PaymentDate + "</td>";
						 output += "<td>" + Amount + "</td>";
						 
						 // buttons
						 output += "<td><input name='btnUpdate' " 
						 + " type='button' value='Update' onclick = ></td>"
						 + "<td><form method='post' action= 'updatePayment.jsp'>"
						 + "<input name='btnRemove' " 
						 + " type='submit' value='Delete'>"
						 + "<input name='BuyerID' type='hidden' " 
						 + " value='" + PaymentID + "'>" 
						 + "</form></td></tr>"; 
					 } 
					 
					// con.close(); 
					
					     // Complete the html table
					     output += "</table>"; 
					 } 
					 
					catch (Exception e) 
					 { 
						 output = "Error while reading the payment details."; 
						 System.err.println(e.getMessage()); 
					 } 
					
					
					return output; 
				}

				// Update buyers in the table
				public String updatePayment(String ID, String username, String email, String address, String connumber, String cname, String cardno, String expdate, String cvv, String paymentdate, String amount)
						{ 
							 String output = ""; 
							 try
							 { 
							 Connection con = connect(); 
							 if (con == null) 
							 {
								 return "Error while connecting to the database for updating."; 
								 
							 } 
							 // create a prepared statement
							 String query = "UPDATE payment SET Name=?,Email=?,Address=?,ContactNumber=?,CardName=?,CreditCardNumber=?,ExpiryDate=?,CVV=?,PaymentDate=?,Amount=? WHERE PaymentID=? ";
								
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 
							 // binding values
							 preparedStmt.setString(1, username); 
							 preparedStmt.setString(2, email); 
							 preparedStmt.setString(3, address); 
							 preparedStmt.setString(4, connumber); 
							 preparedStmt.setString(5, cname); 
							 preparedStmt.setString(6, cardno); 
							 preparedStmt.setString(7, expdate); 
							 preparedStmt.setString(8, cvv);
							 preparedStmt.setString(9, paymentdate);
							 preparedStmt.setString(10, amount);
							 preparedStmt.setInt(11, Integer.parseInt(ID)); 
							 
							 // execute the statement
							    preparedStmt.execute(); 
							    con.close(); 
							    output = "Payment Updated successfully"; 
							 } 
							 
							 catch (Exception e) 
							 { 
							     output = "Error while updating the payment details."; 
							     System.err.println(e.getMessage()); 
							 } 
							 
							 return output; 
							 }
				
				// Delete buyer in the table
				public String deletePayment(String PaymentID) {
					String output = "";

					try {
						Connection con = connect();

						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}

						// create a prepared statement
						String query = "delete from payment where PaymentID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setInt(1, Integer.parseInt(PaymentID));

						// execute the statement
						preparedStmt.execute();
						con.close();

						output = "payment details Deleted successfully";

					} catch (Exception e) {
						output = "Error while deleting the payment details.";
						System.err.println(e.getMessage());
					}

					return output;
				}
				

}
