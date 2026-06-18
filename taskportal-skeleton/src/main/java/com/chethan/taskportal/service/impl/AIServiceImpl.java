package com.chethan.taskportal.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.chethan.taskportal.service.AIService;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String generateDescription(String title) {

        try {

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + apiKey;
            String prompt =
                    "Generate a professional task description for: "
                            + title;

            String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": "%s"
                        }
                      ]
                    }
                  ]
                }
                """.formatted(prompt);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            JsonNode root =
                    objectMapper.readTree(response.getBody());

            return root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {

            System.err.println("Gemini API Error:");
            e.printStackTrace();

            return """
                Task Description:
                Plan, execute, and complete the task efficiently while
                tracking progress and meeting deadlines.

                Priority: Medium
                Estimated Time: 2-4 hours
                """;
        }
    }
}