package com.myfluency.fluency.controller;

import com.myfluency.fluency.model.Progress;
import com.myfluency.fluency.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/{userId}")
    public List<Progress> getProgressByUser(@PathVariable Long userId) {
        return progressService.getProgressByUser(userId);
    }

    @PostMapping
    public Progress updatedProgress(@RequestBody Progress progress) {
        return progressService.updatedProgress(progress);
    }
}
