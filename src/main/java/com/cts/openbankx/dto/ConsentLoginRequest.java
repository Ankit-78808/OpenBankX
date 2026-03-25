package com.cts.openbankx.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsentLoginRequest(
        @NotNull Long consentId,
        @NotBlank @Email String email,
        @NotBlank String phone
) {
}
