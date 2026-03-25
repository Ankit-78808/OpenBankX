
package com.cts.openbankx.model;

import jakarta.persistence.*;

import java.util.List;

import com.cts.openbankx.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AccountRef")
//@Getter @Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class AccountRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private Long accountId;
    
    @Column(name = "UserID", nullable = false)
    private Long userId;

    @Column(name = "AccountNumberMasked", nullable = false, length = 64)
    private String accountNumberMasked;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false, length = 16)
    private AccountType type;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "Status", nullable = false, length = 32)
    private String status;
    

    @OneToMany(mappedBy = "debtorAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PaymentInitiation> debtorPayments;

    @OneToMany(mappedBy = "creditorAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PaymentInitiation> creditorPayments;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FundsCheck> fundsChecks;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionRef> transactions;


    public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNumberMasked() {
		return accountNumberMasked;
	}

	public void setAccountNumberMasked(String accountNumberMasked) {
		this.accountNumberMasked = accountNumberMasked;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}    // Optional back-refs can be added later if needed
}