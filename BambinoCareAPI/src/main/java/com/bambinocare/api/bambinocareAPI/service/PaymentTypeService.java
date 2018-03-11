package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.PaymentType;

public interface PaymentTypeService {

	List<PaymentType> findAll();
	
	PaymentType findByPaymentTypeId(Integer paymentId);
	
}
