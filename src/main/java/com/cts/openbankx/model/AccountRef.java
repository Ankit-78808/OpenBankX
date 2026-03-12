package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

import com.cts.openbankx.enums.AccountType;

@Entity
@Table(name = "AccountRef")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL auto-increment PK
    @Column(name = "AccountID")
    private Long accountId;

    @Column(name = "UserID", nullable = false)
    private Long userId; // Adjust to String if your user IDs are not numeric

    @Column(name = "AccountNumberMasked", nullable = false, length = 64)
    private String accountNumberMasked;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false, length = 16)
    private AccountType type;

    @Column(name = "Currency", nullable = false, length = 3)
    private String currency; // ISO-4217: e.g., "INR", "USD"

    @Column(name = "Status", nullable = false, length = 32)
    private String status; // Left as String since allowed values were not specified

    // --- Optional bidirectional relations for navigation (no cascade/remove by default) ---

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<TransactionRef> transactions;

    @OneToMany(mappedBy = "debtorAccount", fetch = FetchType.LAZY)
    private Set<PaymentInitiation> outgoingPayments;

    @OneToMany(mappedBy = "creditorAccount", fetch = FetchType.LAZY)
    private Set<PaymentInitiation> incomingPayments;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<FundsCheck> fundsChecks;
}