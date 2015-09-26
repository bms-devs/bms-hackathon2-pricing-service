package org.bmshackathon;

import java.math.BigDecimal;

public class VideoPrice {
    private Long id;
    private BigDecimal price;

    public VideoPrice(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
