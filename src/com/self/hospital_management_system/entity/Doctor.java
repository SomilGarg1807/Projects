package com.self.hospital_management_system.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.self.hospital_management_system.connection.Hospital_Connection;

public class Doctor {

	int id;
	String name;
	String specialisation;
	
	public Doctor(int id, String name, String specialisation) {
		super();
		this.id = id;
		this.name = name;
		this.specialisation = specialisation;
	}
	
	static Connection conn = Hospital_Connection.get_Connection();

	public static void addDoctor() {
		Scanner sc = new Scanner(System.in);
		final String INSERT_DOCTOR_QUERY = "insert into doctors(name,specialisation) values(?,?)";
		
		System.out.print("Enter Doctor name: ");
		String name = sc.next();
		System.out.print("Enter Doctor Specialisation: ");
		String specialisation = sc.next();
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_DOCTOR_QUERY);
			ps.setString(1,name);
			ps.setString(2, specialisation);
			
			int rows_affected = ps.executeUpdate();
			
			if(rows_affected!=0) {
				System.err.println("Doctor added Successfully..");
			}else {
				System.err.println("Failed to add Doctor!!! Try again");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void viewDoctor() {
		final String VIEW_Doctor_QUERY = "select * from doctors";
		try {
			PreparedStatement ps = conn.prepareStatement(VIEW_Doctor_QUERY);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("--------------------Doctors-------------------");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String specialisation = rs.getString("specialisation");
				
				System.out.println("Id=> "+id + " Name=> "+name+" Specialisation=> "+specialisation);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean getDoctorById(int id) {
		final String DISPLAY_Doctor_BY_ID = "select * from doctors where id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(DISPLAY_Doctor_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
}
