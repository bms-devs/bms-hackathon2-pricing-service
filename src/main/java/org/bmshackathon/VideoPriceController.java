package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class VideoPriceController {

    @RequestMapping(value = "/videoPrices/{videoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoPrice> findOne(@PathVariable Long videoId) {
        return new ResponseEntity<>(new VideoPrice(123L, BigDecimal.valueOf(456)), HttpStatus.OK);
    }
}
