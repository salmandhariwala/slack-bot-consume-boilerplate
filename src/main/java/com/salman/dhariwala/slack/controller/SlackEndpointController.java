package com.salman.dhariwala.slack.controller;

import java.util.HashMap;
import java.util.Map;

import com.salman.dhariwala.slack.response.processor.EventResponse;
import com.salman.dhariwala.slack.response.processor.InteractionResponse;
import com.salman.dhariwala.slack.response.processor.SlashCommandResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController()
public class SlackEndpointController {

	Logger logger = LoggerFactory.getLogger(SlackEndpointController.class);

	@Autowired
	SlashCommandResponse slashResponse;

	@Autowired
	EventResponse eventResponse;

	@Autowired
	InteractionResponse interactionResponse;

	@RequestMapping(value = "${slack.webhook.endpoint.slash}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> slashHandler(@RequestBody MultiValueMap<String, String> formData) {

		try {
			String slashPayload = new Gson().toJson(formData);
			logger.debug("slash command payload received : "+slashPayload);
			slashResponse.processSlashCommand(slashPayload);
		} catch (Exception e) {
			logger.error("Error while processing slash command : ",e);
		}

		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "${slack.webhook.endpoint.event}", method = RequestMethod.POST)
	public ResponseEntity eventHandler(@RequestBody String jsonDataString) {

		Map<String, Object> responseMap = new HashMap<>();
		try {
			logger.info("event payload received : "+jsonDataString);
			Map payload = new Gson().fromJson(jsonDataString,Map.class);
			if (payload.get("type") != null && payload.get("type").toString().equals("url_verification")) {
				responseMap.put("challenge", payload.get("challenge"));
			}else{
				eventResponse.processEvent(jsonDataString);
			}
		} catch (Exception e) {
			logger.error("Error while processing event webhook : ",e);
		}

		ResponseEntity response = new ResponseEntity(responseMap, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "${slack.webhook.endpoint.interact}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> interactHandler(@RequestParam Map<String, Object> jsonData) {


		try {
			String payload = jsonData.get("payload").toString();
			logger.info("interaction webhook payload received : "+payload);
			interactionResponse.processInteraction(payload);
		} catch (Exception e) {
			logger.error("Error while processing interact webhook : ",e);
		}

		ResponseEntity response = new ResponseEntity<>( HttpStatus.OK);
		return response;
	}

}
