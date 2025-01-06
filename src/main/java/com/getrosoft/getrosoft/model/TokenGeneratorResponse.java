package com.getrosoft.getrosoft.model;

import java.time.Instant;

public record TokenGeneratorResponse (
     Instant createdAt,
     String trackingNumber
){}
