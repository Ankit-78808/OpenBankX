
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
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
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundsCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FundsCheckID")
    private Long fundsCheckId;

    @Column(name = "TPPAppID", nullable = false, length = 64)
    private String tppAppId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "AccountID",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private AccountRef account;

    @Column(name = "Amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "Result", nullable = false, length = 16)
    private FundsCheckResult result; // SUFFICIENT/INSUFFICIENT

    @Column(name = "CheckedDate", nullable = false)
    private LocalDateTime checkedDate;

    @PrePersist
    void prePersist() {
        if (checkedDate == null) {
            checkedDate = LocalDateTime.now();
        }
    }
}
