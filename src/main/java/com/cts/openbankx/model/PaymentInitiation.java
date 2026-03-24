
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cts.openbankx.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
    name = "PaymentInitiation"
//  , indexes = {
//      @Index(name = "idx_payment_tpp", columnList = "TPPAppID"),
//      @Index(name = "idx_payment_created", columnList = "CreatedDate"),
//      @Index(name = "idx_payment_debtor", columnList = "DebtorAccountRef"),
//      @Index(name = "idx_payment_creditor", columnList = "CreditorAccountRef")
//    }
)
//@Getter @Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class PaymentInitiation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long paymentId;

    @Column(name = "TPPAppID", nullable = false, length = 64)
    private String tppAppId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DebtorAccountRef", nullable = false,
                foreignKey = @ForeignKey(name = "fk_payment_debtor_account"))
    private AccountRef debtorAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreditorAccountRef", nullable = false,
                foreignKey = @ForeignKey(name = "fk_payment_creditor_account"))
    private AccountRef creditorAccount;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false, length = 16)
    private PaymentStatus status;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDateTime createdDate;


    public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getTppAppId() {
		return tppAppId;
	}

	public void setTppAppId(String tppAppId) {
		this.tppAppId = tppAppId;
	}

	public AccountRef getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(AccountRef debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public AccountRef getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(AccountRef creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
    @PrePersist
    void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.CREATED;
        }
    }
}
