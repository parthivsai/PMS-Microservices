package com.springboot.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.model.Doctor;
import com.springboot.microservices.repository.DoctorRepository;

@Service
public class DoctorService{
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getallDoctors() {
		return doctorRepository.findAll();
	}
	
	public Doctor getDoctor(int id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	
	public void deleteDoctor(Doctor doctor) {
		doctorRepository.delete(doctor);
	}

	public Doctor getByUsername(String userName) {
		
		return doctorRepository.getByUserName(userName);
		
	}
	
}
