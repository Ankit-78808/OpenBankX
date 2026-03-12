
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cts.openbankx.enums.PaymentStatus;

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
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInitiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long paymentId;

    @Column(name = "TPPAppID", nullable = false, length = 64)
    private String tppAppId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "DebtorAccountRef",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private AccountRef debtorAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "CreditorAccountRef",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private AccountRef creditorAccount;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false, length = 16)
    private PaymentStatus status; // CREATED/AUTHORIZED/REJECTED/EXECUTED

    @Column(name = "CreatedDate", nullable = false)
    private LocalDateTime createdDate;

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
