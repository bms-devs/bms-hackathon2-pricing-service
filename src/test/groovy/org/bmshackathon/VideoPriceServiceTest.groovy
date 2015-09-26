package org.bmshackathon

import org.bmshackathon.reviews.VideoReview
import org.bmshackathon.reviews.VideoReviewClient
import spock.lang.Specification

class VideoPriceServiceTest extends Specification {

    def VideoReviewClient videoReviewClient = Mock(VideoReviewClient)
    def VideoPriceService videoPriceService = new VideoPriceService(videoReviewClient)

    def "should return only base price if no reviews"() {
        setup:
        videoReviewClient.findAll(videoId) >> Collections.emptyList()

        expect:
        videoPriceService.videoPrice(videoId) == expectedPrice

        where:
        videoId | expectedPrice
        1L      | new BigDecimal("10.99")
        2L      | new BigDecimal("20.99")
        5L      | new BigDecimal("50.99")
    }

    def "should multiply by coefficient if some reviews exist"() {
        setup:
        videoReviewClient.findAll(videoId) >> reviews

        expect:
        videoPriceService.videoPrice(videoId) == expectedPrice

        where:
        videoId | reviews                                                                    | expectedPrice
        1L      | [new VideoReview(id: 223, rating: 8)]                                      | new BigDecimal("14.99")
        2L      | [new VideoReview(id: 223, rating: 8)]                                      | new BigDecimal("28.99")
        2L      | [new VideoReview(id: 223, rating: 4)]                                      | new BigDecimal("24.99")
        2L      | [new VideoReview(id: 223, rating: 4), new VideoReview(id: 223, rating: 4)] | new BigDecimal("29.79")
    }
}
