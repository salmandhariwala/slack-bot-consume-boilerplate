package com.salman.dhariwala.slack.response.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class EventResponse {

    Logger logger = LoggerFactory.getLogger(EventResponse.class);

    public void processEvent(String eventload){

        logger.info("Received event for processing : "+eventload);
        // start responding for your slash commands here

    }

}
