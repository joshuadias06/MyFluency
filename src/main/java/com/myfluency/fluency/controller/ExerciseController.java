package com.myfluency.fluency.controller;

import com.myfluency.fluency.model.Exercise;
import com.myfluency.fluency.repository.ExerciseRepository;
import com.myfluency.fluency.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exercicies")
public class ExerciseController {

    @Autowired
    private final ExerciseRepository exerciseRepository;
    @Autowired
    private ExerciseService exerciseService;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/{difficulty}")
    public List<Exercise> getExerciseByDifficulty(@PathVariable String difficulty){
        return exerciseRepository.findByDifficulty(difficulty);
    }

    @PostMapping("/validate")
    public boolean validateAnswer(@RequestBody Map<String, String> userAnswer){
        String questionId = userAnswer.get("id");
        String answer = userAnswer.get("answer");
        return exerciseService.validateAnswer(Long.parseLong(questionId), answer);
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise){
        return exerciseService.createExercise(exercise);
    }

}
