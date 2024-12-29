package com.myfluency.fluency.service;

import com.myfluency.fluency.model.Exercise;
import com.myfluency.fluency.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getExerciseByDifficulty(String difficulty) {
        return exerciseRepository.findByDifficulty(difficulty);
    }

    public boolean validateAnswer(long id, String answer) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        return exercise.getCorrectAnswer().equalsIgnoreCase(answer);
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
