package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.KafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping
@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final KafkaProducer kafkaProducer;
	
	@RequestMapping("/ping/send")
	public void ping() {
		log.info("Message send");
		Map<String, Object> data = new HashMap<>();
		data.put("Hello,", "World!");
		data.put("kdw", "29");
		kafkaProducer.produceMessage(data);
	}

}
