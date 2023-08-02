package com.springboot.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservices.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

}
