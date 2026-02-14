package com.weather.app.backend.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface OpenAiService {
	
	@SystemMessage("Você é um assistente meteorológico claro, confiável e objetivo.")
	String responder(String solicitacao);
		
}
