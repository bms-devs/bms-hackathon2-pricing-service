package org.bmshackathon;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VideoPriceService {

    public BigDecimal videoPrice(final Long videoId) {
        return BigDecimal.valueOf(videoId)
                .multiply(BigDecimal.TEN)
                .add(BigDecimal.valueOf(0.99));
    }
}
