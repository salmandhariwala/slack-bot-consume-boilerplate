package com.salman.dhariwala.slack.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

	@GetMapping
	@RequestMapping("/")
	public void healthCheck() {

	}

}
