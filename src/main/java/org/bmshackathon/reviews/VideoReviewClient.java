package org.bmshackathon.reviews;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("review-service")
public interface VideoReviewClient {

    @RequestMapping(value = "/findByVideoId/{id}", method = RequestMethod.GET)
    List<VideoReview> findAll(@PathVariable("id") Long id);
}
