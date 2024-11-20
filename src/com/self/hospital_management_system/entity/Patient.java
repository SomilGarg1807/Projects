package com.self.hospital_management_system.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.self.hospital_management_system.connection.Hospital_Connection;

public class Patient {

	 int id;
	 String name;
	 int age;
	 String gender;
	
	
	public Patient(int id, String name, int age, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	static Connection conn = Hospital_Connection.get_Connection();

	public static void addPatient() {
		Scanner sc = new Scanner(System.in);
		final String INSERT_PATIENT_QUERY = "insert into patients(name,age,gender) values(?,?,?)";
		
		System.out.print("Enter patient name: ");
		String name = sc.next();
		System.out.print("Enter patient age: ");
		int age = sc.nextInt();
		System.out.print("Enter patient gender(M/F): ");
		String gender = sc.next();
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_PATIENT_QUERY);
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			
			int rows_affected = ps.executeUpdate();
			
			if(rows_affected!=0) {
				System.err.println("Patient added Successfully..");
			}else {
				System.err.println("Failed to add Patient!!! Try again");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void viewPatient() {
		final String VIEW_PATIENT_QUERY = "select * from patients";
		try {
			PreparedStatement ps = conn.prepareStatement(VIEW_PATIENT_QUERY);
			ResultSet rs = ps.executeQuery();
			
			List<Patient> patients = new ArrayList<>();
			System.out.println("--------------------Patients-------------------");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				
				System.out.println("Id=> "+id + " Name=> "+name+" Age=> "+age+" Gender=> "+gender );
			}
			
//			for(Patient ps1:patients) {
//				System.out.println(ps1.toString());
//			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean getPatientById(int id) {
		final String DISPLAY_PATIENT_BY_ID = "select * from patients where id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(DISPLAY_PATIENT_BY_ID);
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
