package com.example.pharma.domain.entities.keys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KeyStock implements Serializable {

    private Long productId;
    private Long warehouseId;
}
