package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_type_id", unique = true, nullable = false)
	private Integer paymentTypeId;

	@Column(name = "payment_type_desc", nullable = false)
	private String paymentTypeDesc;

	public PaymentType() {
	}

	public PaymentType(Integer paymentTypeId, String paymentTypeDesc) {
		this.paymentTypeId = paymentTypeId;
		this.paymentTypeDesc = paymentTypeDesc;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}

	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
	}

}