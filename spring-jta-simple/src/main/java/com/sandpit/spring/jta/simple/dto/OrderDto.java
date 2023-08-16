package com.sandpit.spring.jta.simple.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrderDto implements Serializable {
    private String item;
    private int quantity;
    private boolean hasFault;
}
