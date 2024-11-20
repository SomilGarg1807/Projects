package com.self.hospital_management_system.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.self.hospital_management_system.connection.Hospital_Connection;
import com.self.hospital_management_system.entity.Doctor;
import com.self.hospital_management_system.entity.Patient;

public class HospitalController {
	
	static Connection conn = Hospital_Connection.get_Connection();
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("================Welcome to Fortis Escort Hospital System================");
		
		while(true) {
		System.out.println("Enter 1 to add patient");
		System.out.println("Enter 2 to view patient");
		System.out.println("Enter 3 to add doctor");
		System.out.println("Enter 4 to view Doctor");
		System.out.println("Enter 5 to book appointment");
		System.out.println("Enter 6 to exit");
		System.out.println("==============================================");
		System.out.println("Enter your choice-:");
		int ch = sc.nextInt();
		
		switch(ch){
			case 1:
				Patient.addPatient();
				System.out.println();
				break;
				
			case 2:
				Patient.viewPatient();
				System.out.println();
				break;
				
			case 3:
				Doctor.addDoctor();
				System.out.println();
				break;
				
			case 4:
				Doctor.viewDoctor();
				System.out.println();
				break;
			
			case 5:
				bookAppointment();
				System.out.println();
				break;
				
			case 6:
				return;
			
			default:
			 System.out.println("Enter a valid choice");	
		}
		}
	}
	
	public static void bookAppointment() {
		
		final String Query_To_INSERT_IN_APPOINTMENT = "insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
		Scanner sc  = new Scanner(System.in);
		System.out.println("Enter Patient ID: ");
		int patient_id = sc.nextInt();
		System.out.println("Enter Doctor ID ");
		int doctor_id = sc.nextInt();
		System.out.println("Enter Appointment Date: ");
		String appointment_date = sc.next();
		
		if(Patient.getPatientById(patient_id) && Doctor.getDoctorById(doctor_id)) {
			if(checkAvailability(doctor_id,appointment_date)) {
				try {
					PreparedStatement pss = conn.prepareStatement(Query_To_INSERT_IN_APPOINTMENT);
					pss.setInt(1, patient_id);
					pss.setInt(2, doctor_id);
					pss.setString(3, appointment_date);
					
					
					int result=pss.executeUpdate();
					if(result!=0) {
						System.out.println("Appointment Booked");
					}
					else {
						System.out.println("Failed to book Appointment...");
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
				
				
			}else {
				System.out.println("Doctor not Available on "+appointment_date);
			}
			
		}else {
			System.out.println("Invalid patient id or Doctor Id");
		}
	}
	
	public static boolean checkAvailability(int doctor_id,String appointment_date) {
		
		final String QUERY = "select COUNT(*) from appointments where doctor_id=? and appointment_date = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY);
			ps.setInt(1, doctor_id);
			ps.setString(2, appointment_date);
			
			ResultSet rss = ps.executeQuery();
			if(rss.next()) {
				int count = rss.getInt(1);
				if(count< 5) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
}
