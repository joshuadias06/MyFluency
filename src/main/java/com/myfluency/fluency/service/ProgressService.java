package com.myfluency.fluency.service;

import com.myfluency.fluency.model.Progress;
import com.myfluency.fluency.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    public List<Progress> getProgressByUser(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    public Progress updatedProgress(Progress progress) {
        Progress existingProgress = progressRepository
                .findByUserIdAndDifficulty(progress.getUserId(), progress.getDifficulty())
                .orElse(new Progress(progress.getUserId(), progress.getDifficulty(), 0, 0));


        existingProgress.setTotalExercise(existingProgress.getTotalExercise() + 1);

        if (progress.getCorrectAnswer() > 0) { // Considerar o valor da entrada "progress"
            existingProgress.setCorrectAnswer(existingProgress.getCorrectAnswer() + 1);
        }

        // Salvar as mudanças no repositório
        return progressRepository.save(existingProgress);
    }
}

