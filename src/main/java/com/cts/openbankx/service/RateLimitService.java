package com.cts.openbankx.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.openbankx.model.RateLimit;
import com.cts.openbankx.repository.RateLimitRepo;

	@Service
	public class RateLimitService {

	    @Autowired
	    private RateLimitRepo rateLimitRepo;

	    public RateLimit create(RateLimit rl) {
	        return rateLimitRepo.save(rl);
	    }


	    public RateLimit getById(Long id) {
	        return rateLimitRepo.findById(id)
	                .orElseThrow(() -> new NoSuchElementException("RateLimit not found: " + id));
	    }


	    public List<RateLimit> getAll() {
	        return rateLimitRepo.findAll();
	    }


//	    public RateLimit update(Long id, RateLimit input) {
//	        RateLimit existing = rateLimitRepo.findById(id)
//	                .orElseThrow(() -> new NoSuchElementException("RateLimit not found: " + id));
//
//	        existing.setTPPAppID(input.getTPPAppID());
//	        existing.setPeriodStart(input.getPeriodStart());
//	        existing.setCount(input.getCount());
//	        existing.setLimitWindow(input.getLimitWindow());
//
//	        return rateLimitRepo.save(existing);
//	    }

	    
	    public void delete(Long id) {
	        if (!rateLimitRepo.existsById(id)) {
	            throw new NoSuchElementException("RateLimit not found: " + id);
	        }
	        rateLimitRepo.deleteById(id);
	    }
	}
