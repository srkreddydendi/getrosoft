package com.getrosoft.getrosoft.service;

import com.getrosoft.getrosoft.model.TokenGeneratorResponse;
import com.getrosoft.getrosoft.util.AppUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class TokenGeneratorService {

    private static final Function<String, Character> extractFirstChar = str -> str.charAt(0);

    public TokenGeneratorResponse getNextToken(String originCountryId,
                                               String destinationCountryId,
                                               Double weight,
                                               Instant createdAt,
                                               UUID customerId,
                                               String customerName,
                                               String customerSlug
    ) {
        Instant createdTs = Optional.ofNullable(createdAt).orElse(Instant.now());
        return new TokenGeneratorResponse(
                createdTs,
                generateToken(originCountryId, destinationCountryId, weight, createdTs, customerId, customerName, customerSlug)
        );
    }

    private String generateToken(String originCountryId,
                                 String destinationCountryId,
                                 Double weight,
                                 Instant createdAt,
                                 UUID customerId,
                                 String customerName,
                                 String customerSlug

    ) {

        String originCountry = Optional.ofNullable(originCountryId).orElse(extractChar.get());
        String destinationCountry = Optional.ofNullable(destinationCountryId).orElse(extractChar.get());
        Double wgt = Optional.ofNullable(weight).orElse(extractInt.get());
        long createdInstant = createdAt.getNano();
        String customerIdSplit = (Optional.ofNullable(customerId).orElse(UUID.randomUUID())).toString().split("-")[0];
        String custName = Optional.ofNullable(customerName).orElse(extractChar.get());
        String custSlug = Optional.ofNullable(customerSlug).orElse(extractChar.get());

        String tokenFormat = "%s%s%s%s%s%s%s";
        synchronized (this) {
            return String.format(tokenFormat,
                    extractFirstChar.apply(originCountry),
                    extractFirstChar.apply(destinationCountry),
                    extractFirstChar.apply(wgt.toString()),
                    AppUtil.compress(createdInstant),
                    customerIdSplit.substring(0, customerIdSplit.length() - 3),
                    extractFirstChar.apply(custName),
                    extractFirstChar.apply(custSlug)).toUpperCase();
        }
    }

    private Supplier<String> extractChar = () -> {
        Random random = new Random();
        return String.valueOf(AppUtil.CHAR_SET.charAt(random.nextInt(61)));
    };

    private Supplier<Double> extractInt = () -> {
        Random random = new Random(62);
        return random.nextDouble();
    };
}
