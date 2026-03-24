
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cts.openbankx.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
    name = "TransactionRef"
//  , indexes = {
//      @Index(name = "idx_txn_account", columnList = "AccountID"),
//      @Index(name = "idx_txn_date", columnList = "TxnDate")
//    }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRef {


@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TxnRefID")
    private Long txnRefId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountID", nullable = false,
                foreignKey = @ForeignKey(name = "fk_transaction_account"))
    private AccountRef account;

    @Column(name = "TxnDate", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TxnType", nullable = false, length = 8)
    private TransactionType txnType;

    @Column(name = "Narrative", length = 512)
    private String narrative;


    public Long getTxnRefId() {
		return txnRefId;
	}

	public void setTxnRefId(Long txnRefId) {
		this.txnRefId = txnRefId;
	}

	public AccountRef getAccount() {
		return account;
	}

	public void setAccount(AccountRef account) {
		this.account = account;
	}

	public LocalDateTime getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDateTime txnDate) {
		this.txnDate = txnDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
}
