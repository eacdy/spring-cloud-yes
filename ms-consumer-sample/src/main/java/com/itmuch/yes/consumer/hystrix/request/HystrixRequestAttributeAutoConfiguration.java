package com.itmuch.yes.consumer.hystrix.request;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.Hystrix;

/**
 * @author Reno Chou
 */
@Configuration
@ConditionalOnClass({ Hystrix.class })
@ConditionalOnProperty(value = "hystrix.propagate.request-attribute.enabled", matchIfMissing = true)
@EnableConfigurationProperties(HystrixRequestAttributeProperties.class)
public class HystrixRequestAttributeAutoConfiguration {

	@Bean
	public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration() {
		return new RequestAttributeHystrixConcurrencyStrategy();
	}
}
