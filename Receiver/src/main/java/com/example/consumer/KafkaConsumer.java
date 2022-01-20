package com.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
	@KafkaListener(topics = "${ping.topic.name}", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listenMessage(Object message, Acknowledgment ack) {
        try {
            System.out.println("Recieved ping message: " + message.toString());
            ack.acknowledge();
        } catch (Exception e) {
        	String msg = "시스템에 예상치 못한 문제가 발생했습니다";
        	System.out.println("Recieved ping message: " + msg + e);
        }
    }
}
