package com.springboot.microservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.microservices.JwtTokenProvider;
//import com.springboot.microservices.model.Doctor;
//import com.springboot.microservices.model.Patient;
import com.springboot.microservices.model.User;
import com.springboot.microservices.repository.UserRepository;

@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider jwtProvider;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Autowired
//	private DoctorService doctorService;
//	
//	@Autowired
//	private PatientService patientService;
	

	public User insert(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<Object> generateJwtToken(String userName, String password) {
        User user = userRepository.getUserByUsername(userName);
//        System.out.println(user);
       
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            String token =  jwtProvider.generateToken(userName, user.getRole());
            System.out.println(token);
            
            List<Object> userDetails = new ArrayList<>();
            userDetails.add(token);
            userDetails.add(user.getRole());
            System.out.println(user.getRole());
            
//            System.out.println("Token: " + token + " Role: " + user.getRole());
            
//            if(user.getRole().equals("DOCTOR")) {
//            	System.out.println("Check for username " + userName);
//            	Doctor doc = doctorService.getByUsername(userName);
//            	userDetails.add(doc);
//            	System.out.println("in doc loop" + doc);
//            	return userDetails;
//            }else if(user.getRole().equals("PATIENT")) {
//            	Patient patient = patientService.getByUsername(userName);
//            	userDetails.add(patient);
//            	System.out.println(patient);
//            	return userDetails;}else
            {
            	User getUser = getByUsername(userName);
            	userDetails.add(getUser);
            	return userDetails;
            }
        }
        return null;
    }

	public User getByUsername(String userName) {
		return userRepository.getUserByUsername(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		return user;
	}

	public User getById(int userId) {
		Optional<User> optional= userRepository.findById(userId);
		if(!optional.isPresent()) {
			return null; 
		}
		return optional.get();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}

}
