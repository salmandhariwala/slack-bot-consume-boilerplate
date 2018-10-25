package com.salman.dhariwala.slack.response.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class InteractionResponse {

    Logger logger = LoggerFactory.getLogger(InteractionResponse.class);

    public void processInteraction(String interactionPayload){
        logger.info("Received Interaction event : "+interactionPayload);
        // start writing your code
    }

}
