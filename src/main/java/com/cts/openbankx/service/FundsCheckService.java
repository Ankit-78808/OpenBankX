package com.cts.openbankx.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.enums.ConsentStatus;
import com.cts.openbankx.enums.FundsCheckResult;
import com.cts.openbankx.enums.TxnType;
import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.FundsCheck;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.model.TransactionRef;
import com.cts.openbankx.repository.AccountRefRepository;
import com.cts.openbankx.repository.FundsCheckRepository;
import com.cts.openbankx.security.RequestSecurityContext;

@Service
public class FundsCheckService {

    private final FundsCheckRepository repo;
    private final AccountRefRepository accountRepo;
    private final TransactionRefService txnService;

    public FundsCheckService(
            FundsCheckRepository repo,
            AccountRefRepository accountRepo,
            TransactionRefService txnService) {

        this.repo = repo;
        this.accountRepo = accountRepo;
        this.txnService = txnService;
    }

    public FundsCheck performFundsCheck(FundsCheck input) {

        Token token = RequestSecurityContext.getToken();
        TPPApp tppApp = RequestSecurityContext.getTppApp();

        if (token == null || token.getUser() == null) {
            throw new RuntimeException("No security context available");
        }

        // ✅ EXTRACT STRING VALUE FROM AccountRef
        String accountNumber =
            input.getAccountRef().getAccountNumberMasked();

        // ✅ LOAD MANAGED ENTITY
        AccountRef account = accountRepo
                .findByAccountNumberMasked(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        BigDecimal balance = txnService
                .findByAccountId(account.getAccountId())
                .stream()
                .map(txn ->
                        txn.getTxnType() == TxnType.CREDIT
                                ? txn.getAmount()
                                : txn.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        FundsCheckResult result =
                balance.compareTo(input.getAmount()) >= 0
                        ? FundsCheckResult.SUFFICIENT
                        : FundsCheckResult.INSUFFICIENT;

        FundsCheck check = new FundsCheck();
        check.setTppApp(tppApp);
        check.setAccountRef(account);      // ✅ MANAGED ENTITY
        check.setAmount(input.getAmount());
        check.setCurrency(input.getCurrency());
        check.setResult(result);
        check.setCheckedDate(LocalDateTime.now());

        return repo.save(check);
    }

	// --- CRUD ---
	public List<FundsCheck> findAll() {
		return repo.findAll();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}