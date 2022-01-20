package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaPublisherConfig {
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootServer;
	
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
    	Map<String, Object> propsConfig = new HashMap<>();
        
    	propsConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootServer);
    	propsConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	propsConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    	
        return new DefaultKafkaProducerFactory<>(propsConfig);
    }
    
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
    	return new KafkaTemplate<>(producerFactory());
    }
}
