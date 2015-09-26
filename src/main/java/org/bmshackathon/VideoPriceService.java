package org.bmshackathon;

import org.bmshackathon.reviews.VideoReview;
import org.bmshackathon.reviews.VideoReviewClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VideoPriceService {

    private final VideoReviewClient videoReviewClient;

    @Autowired
    public VideoPriceService(final VideoReviewClient videoReviewClient) {
        this.videoReviewClient = videoReviewClient;
    }

    public BigDecimal videoPrice(final Long videoId) {
        return basePrice(videoId)
                .multiply(reviewCoefficient(videoId))
                .add(someCents());
    }

    private BigDecimal basePrice(final Long videoId) {
        return BigDecimal
                .valueOf(videoId)
                .multiply(BigDecimal.TEN);
    }

    private BigDecimal reviewCoefficient(final Long videoId) {
        final List<VideoReview> videoReviews = videoReviewClient.findAll(videoId);

        return videoReviews
                .stream()
                .map(this::reviewWeight)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    private BigDecimal reviewWeight(final VideoReview videoReview) {
        return BigDecimal
                .valueOf(videoReview.getRating())
                .divide(BigDecimal.valueOf(20), 2, BigDecimal.ROUND_HALF_UP)
                .add(BigDecimal.ONE);
    }

    private BigDecimal someCents() {
        return BigDecimal.valueOf(0.99);
    }
}
