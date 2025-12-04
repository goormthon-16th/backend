package com.goormthon.backend.controller;

import com.goormthon.backend.entity.TestEntity;
import com.goormthon.backend.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/ping")
    public String ping() {
        return "pong22";
    }

    @PostMapping("/create")
    public TestEntity create(@RequestBody TestEntity data) {
        return testRepository.save(data);
    }

    @GetMapping("/{id}")
    public TestEntity get(@PathVariable Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @GetMapping("/healthz")
    public String healthz() {
        return "OK";
    }
}
