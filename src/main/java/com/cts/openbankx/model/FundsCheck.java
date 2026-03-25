
package com.cts.openbankx.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cts.openbankx.enums.FundsCheckResult;

@Entity
@Table(
    name = "FundsCheck"
//  , indexes = {
//      @Index(name = "idx_fundscheck_tpp", columnList = "TPPAppID"),
//      @Index(name = "idx_fundscheck_account", columnList = "AccountID"),
//      @Index(name = "idx_fundscheck_checked", columnList = "CheckedDate")
//    }
)
//@Getter @Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class FundsCheck {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FundsCheckID")
    private Long fundsCheckId;

    @Column(name = "TPPAppID", nullable = false, length = 64)
    private String tppAppId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountID", nullable = false,
                foreignKey = @ForeignKey(name = "fk_fundscheck_account"))
    private AccountRef account;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "Result", nullable = false, length = 16)
    private FundsCheckResult result;

    @Column(name = "CheckedDate", nullable = false)
    private LocalDateTime checkedDate;

	  
	  public Long getFundsCheckId() {
		return fundsCheckId;
	}

	public void setFundsCheckId(Long fundsCheckId) {
		this.fundsCheckId = fundsCheckId;
	}

	public String getTppAppId() {
		return tppAppId;
	}

	public void setTppAppId(String tppAppId) {
		this.tppAppId = tppAppId;
	}

	public AccountRef getAccount() {
		return account;
	}

	public void setAccount(AccountRef account) {
		this.account = account;
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

	public FundsCheckResult getResult() {
		return result;
	}

	public void setResult(FundsCheckResult result) {
		this.result = result;
	}

	public LocalDateTime getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(LocalDateTime checkedDate) {
		this.checkedDate = checkedDate;
	}

    @PrePersist
    void prePersist() {
        if (checkedDate == null) {
            checkedDate = LocalDateTime.now();
        }
    }
}
