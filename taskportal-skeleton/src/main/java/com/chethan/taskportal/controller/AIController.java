package com.chethan.taskportal.controller;

import com.chethan.taskportal.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/generate")
    public String generate(@RequestParam String title) {
        return aiService.generateDescription(title);
    }
}