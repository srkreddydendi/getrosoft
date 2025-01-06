package com.getrosoft.getrosoft.controller;

import com.getrosoft.getrosoft.service.TokenGeneratorService;
import com.getrosoft.getrosoft.util.ErrorCodes;
import com.getrosoft.getrosoft.util.LocaleValidator;
import com.getrosoft.getrosoft.validation.ValidWeight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class TokenGeneratorController {

    private final TokenGeneratorService tokenGeneratorService;

    private final LocaleValidator localeValidator;

    public TokenGeneratorController(TokenGeneratorService tokenGeneratorService,
                                    LocaleValidator localeValidator) {
        this.tokenGeneratorService = tokenGeneratorService;
        this.localeValidator = localeValidator;
    }

    @GetMapping(value = "next-tracking-number")
    public ResponseEntity<?> getNextToken(
            @RequestParam(value = "origin_country_id", required = false) String originCountryId,
            @RequestParam(value = "destination_country_id", required = false) String destinationCountryId,
            @RequestParam(value = "weight", required = false) @ValidWeight Double weight,
            @RequestParam(value = "created_at", required = false) Instant createdAt,
            @RequestParam(value = "customer_id", required = false) UUID customerId,
            @RequestParam(value = "customer_name", required = false) String customerName,
            @RequestParam(value = "customer_slug", required = false) String customerSlug
    ) {
        if (!localeValidator.areValidCountryCodes(originCountryId, destinationCountryId)) {
            return new ResponseEntity<>(ErrorCodes.INVALID_COUNTRY_CODES, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tokenGeneratorService.getNextToken(originCountryId, destinationCountryId, weight, createdAt, customerId, customerName, customerSlug), HttpStatus.OK);
    }
}
