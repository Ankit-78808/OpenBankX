
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cts.openbankx.enums.TransactionType;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "AccountID",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private AccountRef account;

    @Column(name = "TxnDate", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TxnType", nullable = false, length = 8)
    private TransactionType txnType; // DEBIT or CREDIT

    @Column(name = "Narrative", length = 512)
    private String narrative;
}
