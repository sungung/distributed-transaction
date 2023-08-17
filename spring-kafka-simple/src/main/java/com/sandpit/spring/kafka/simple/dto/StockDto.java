package com.sandpit.spring.kafka.simple.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class StockDto  implements Serializable {
    private String item;
    private int availableQuantity;
}
