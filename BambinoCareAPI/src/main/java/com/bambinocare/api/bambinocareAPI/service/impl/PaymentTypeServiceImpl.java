package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.PaymentType;
import com.bambinocare.api.bambinocareAPI.repository.PaymentTypeRepository;
import com.bambinocare.api.bambinocareAPI.service.PaymentTypeService;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService{

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Override
	public List<PaymentType> findAll() {
		return paymentTypeRepository.findAll();
	}

	@Override
	public PaymentType findByPaymentTypeId(Integer paymentId) {
		return paymentTypeRepository.findById(paymentId).orElse(null);
	}
	
}
