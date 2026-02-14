package com.weather.app.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

@Configuration
public class AiConfig {

	@Bean
	public OpenAiChatModel openAiChatModel(@Value("${langchain4j.open-ai.api-key}") String apiKey) {
		return OpenAiChatModel.builder()
				.apiKey(apiKey)
				.modelName(OpenAiChatModelName.GPT_4_1_MINI)
				.temperature(0.7)
				.build();
	}
}
