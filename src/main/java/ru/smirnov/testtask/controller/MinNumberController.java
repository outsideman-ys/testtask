package ru.smirnov.testtask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnov.testtask.service.MinNumberService;

@RestController
@RequestMapping("/api")
public class MinNumberController {

    private final MinNumberService service;

    public MinNumberController(MinNumberService service) {
        this.service = service;
    }

    @GetMapping("/nth-min")
    @Operation(summary = "Находит минимальное N-ое число из xlsx файла")
    public int getNthMin(
            @Parameter(description = "Полный путь к .xlsx файлу") @RequestParam String filePath,
            @Parameter(description = "Число N") @RequestParam int n) {
        return service.findNthMin(filePath, n);
    }
}