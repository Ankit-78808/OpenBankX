package com.cts.openbankx.controller;

import org.springframework.web.bind.annotation.*;


import com.cts.openbankx.model.FundsCheck;
import com.cts.openbankx.service.FundsCheckService;

@RestController
@RequestMapping("/api/v1/cbpii/funds-check")
public class FundsCheckController {

    private final FundsCheckService service;

    public FundsCheckController(FundsCheckService service) {
        this.service = service;
    }

    /**
     * CBPII: YES / NO funds availability check
     */
    @PostMapping
    public FundsCheck check(@RequestBody FundsCheck input) {
        return service.performFundsCheck(input);
    }
}
