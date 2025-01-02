package com.myfluency.fluency.controller;

import com.myfluency.fluency.model.Text;
import com.myfluency.fluency.service.TextAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text")
public class TextController {

    @Autowired
    private TextAnalysisService textAnalysisService;

    public String submitText(@RequestBody Text text){
        textAnalysisService.saveText(text);

        boolean isCorrect = textAnalysisService.analyzeText(text.getText());
        return isCorrect ? "Texto correto" : "Texto pode ser falado de forma correta assim: ";
    }

}
