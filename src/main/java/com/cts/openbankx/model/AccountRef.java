
package com.cts.openbankx.model;

import jakarta.persistence.*;
import lombok.*;
import com.cts.openbankx.enums.AccountType;

@Entity
@Table(name = "AccountRef")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // Optional back-refs can be added later if needed
}
