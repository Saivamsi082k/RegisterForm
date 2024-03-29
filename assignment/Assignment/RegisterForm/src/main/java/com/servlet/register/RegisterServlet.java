package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	//create query
	private static final String INSERT_QUERY="INSERT INTO USER(NAME,CITY,MOBILE,DOB)VALUES(?,?,?,?)";
	
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	//get printwriter
        	 PrintWriter pw=resp.getWriter();
        	 //set content type
        	 resp.setContentType("text/html");
        	 //read the form values
        	 String name=req.getParameter("name");
        	 String city=req.getParameter("city");
        	 String mobile=req.getParameter("mobile");
        	 String dob=req.getParameter("dob");
        	 System.out.println("Name: "+name);
        	 System.out.println("City: "+city);
        	 System.out.println("mobile: "+mobile);
        	 System.out.println("dob: "+dob);
        	 //load jdbc driver
        	 try {
        		 Class.forName("com.mysql.jdbc.Driver");
        	 }catch (ClassNotFoundException e) {
				 e.printStackTrace();
			}
        	 //create connection
        	 try(Connection con=DriverManager.getConnection("jdbc:mysql:///firstdb","root","733741@Kalyan");
        		 PreparedStatement ps=con.prepareStatement(INSERT_QUERY);){
        		 //set the values
        		 ps.setString(1, name);
        		 ps.setString(2, city);
        		 ps.setString(3, mobile);
        		 ps.setString(4, dob);
        		 //execute the query
        		 int count=ps.executeUpdate();
        		 if (count==0) {
        			 pw.println("Record not stored into database");
					
				}
        		 else {
					pw.println("Record stored into Database");
				}
        		 
        	 }catch(SQLException se) {
        		 pw.println(se.getMessage());
        		 se.printStackTrace();
        	 }catch (Exception e) {
				pw.println(e.getMessage());
				e.printStackTrace();
			}
        	 
        	 //close the stream
        	 pw.close();
        	 
        }
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
        }
}
