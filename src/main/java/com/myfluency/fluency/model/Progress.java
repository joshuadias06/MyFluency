package com.myfluency.fluency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String difficulty;
    private int totalExercise;
    private int correctAnswer;

    public Progress(Long userId, String difficulty, int totalExercise, int correctAnswer) {
        this.userId = userId;
        this.difficulty = difficulty;
        this.totalExercise = totalExercise;
        this.correctAnswer = correctAnswer;
    }

}
