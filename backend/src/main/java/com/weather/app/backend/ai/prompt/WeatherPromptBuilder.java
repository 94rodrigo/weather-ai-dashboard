package com.weather.app.backend.ai.prompt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.backend.dto.ai.AiWeatherContext;

public class WeatherPromptBuilder {

	private final ObjectMapper objectMapper;

    public WeatherPromptBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String build(AiWeatherContext context, PromptIntentEnum intent) {
        return switch (intent) {
            case GENERAL_SUMMARY -> buildGeneralSummary(context);
            case OUTDOOR_ACTIVITY -> buildOutdoorActivity(context);
            case CLOTHING_RECOMMENDATION -> buildClothing(context);
            case ALERT_ONLY -> buildAlertOnly(context);
        };
    }

    // ===================== PROMPTS =====================

    private String buildGeneralSummary(AiWeatherContext context) {
        return """
        Você é um assistente meteorológico claro, confiável e objetivo.

        Com base EXCLUSIVAMENTE nos dados fornecidos:
        1. Faça um resumo curto do clima atual em %s.
        2. Destaque riscos ou desconfortos (calor, chuva, vento ou UV).
        3. Analise as próximas horas e indique mudanças relevantes.
        4. Resuma a previsão dos próximos dias.
        5. Dê uma recomendação prática ao usuário.
        6. O retorno deve ser no idioma %s

        Regras:
        - Não invente informações.
        - Não use termos técnicos.
        - Use linguagem simples e natural.
        - Seja conciso (máx. 6–8 frases).
        - Se houver alertas, priorize-os no início.

        Dados:
        %s
        """.formatted(
                context.getLocation(),
                context.getLanguage(),
                toJson(context)
        );
    }

    private String buildOutdoorActivity(AiWeatherContext context) {
        return """
        Você é um assistente meteorológico.

        Avalie se o clima é favorável para atividades ao ar livre hoje em %s.
        Informe o melhor horário e possíveis riscos.

        Regras:
        - Seja direto.
        - Não invente dados.
        - Linguagem simples.

        Dados:
        %s
        """.formatted(
                context.getLocation(),
                toJson(context)
        );
    }

    private String buildClothing(AiWeatherContext context) {
        return """
        Com base no clima atual e na previsão em %s,
        recomende o tipo de roupa mais adequado para hoje.

        Regras:
        - Considere temperatura, sensação térmica, vento e chuva.
        - Seja prático e objetivo.
        - Não use termos técnicos.

        Dados:
        %s
        """.formatted(
                context.getLocation(),
                toJson(context)
        );
    }

    private String buildAlertOnly(AiWeatherContext context) {
        return """
        Gere um alerta curto e direto para o usuário em %s,
        destacando apenas riscos imediatos relacionados ao clima.

        Regras:
        - Máx. 2 frases.
        - Sem introdução.
        - Se não houver riscos, diga que não há alertas no momento.

        Dados:
        %s
        """.formatted(
                context.getLocation(),
                toJson(context)
        );
    }

    // ===================== UTILS =====================

    private String toJson(AiWeatherContext context) {
        try {
            return objectMapper.writeValueAsString(context);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Erro ao serializar AiWeatherContext", e);
        }
    }
}
