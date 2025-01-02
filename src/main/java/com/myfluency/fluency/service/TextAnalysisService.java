package com.myfluency.fluency.service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.PartOfSpeech;
import com.google.cloud.language.v1.Token;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.myfluency.fluency.model.Text;
import com.myfluency.fluency.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TextAnalysisService {

    @Autowired
    private TextRepository textRepository;

    public void saveText(Text text){
        // Salvando o texto diretamente no banco
        textRepository.save(text);
    }

    public boolean analyzeText(String text){
        try {
            try (LanguageServiceClient language = LanguageServiceClient.create()) {
                Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();

                AnalyzeSyntaxResponse response = language.analyzeSyntax(doc);

                boolean isValidEnglish = true; // Inicializa como verdadeiro, será ajustado com base na análise

                for (Token token : response.getTokensList()) {
                    if (token.getPartOfSpeech().getTag() == PartOfSpeech.Tag.UNKNOWN) {
                        isValidEnglish = false; // Se algum token for desconhecido, considera inválido
                    }
                }

                // Agora cria um objeto Text e persiste no banco
                Text textEntity = new Text();
                textEntity.setContent(text);
                textEntity.setIsValidEnglish(isValidEnglish);

                saveText(textEntity); // Persiste o texto no banco

                return isValidEnglish; // Retorna a validade do texto
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
