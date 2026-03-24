package com.cts.openbankx.service;

import com.cts.openbankx.model.PaymentInitiation;
import com.cts.openbankx.repository.PaymentInitiationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentInitiationService {

	@Autowired
    private PaymentInitiationRepository paymentInitiationRepository;

	public PaymentInitiation getById(Long id) {
        return paymentInitiationRepository.findById(id).orElse(null);
    }

    public List<PaymentInitiation> getAll() {
        return paymentInitiationRepository.findAll();
    }

    public PaymentInitiation create(PaymentInitiation payment) {
        return paymentInitiationRepository.save(payment);
    }

    public PaymentInitiation update(Long id, PaymentInitiation payment) {
        payment.setPaymentId(id);
        return paymentInitiationRepository.save(payment);
    }

    public void delete(Long id) {
        paymentInitiationRepository.deleteById(id);
    }
}