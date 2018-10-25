package com.salman.dhariwala.slack.response.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class SlashCommandResponse {

    Logger logger = LoggerFactory.getLogger(SlashCommandResponse.class);

    public void processSlashCommand(String slashPayload){

        logger.info("Received slash command for processing : "+slashPayload);
        // start writing your code

    }

}
