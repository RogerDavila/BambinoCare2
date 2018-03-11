package com.bambinocare.api.bambinocareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

}
