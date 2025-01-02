package com.myfluency.fluency.service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.PartOfSpeech;
import com.google.cloud.language.v1.Token;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;  // Correção aqui
import com.myfluency.fluency.model.Text;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TextAnalysisService {

    public void saveText(Text text){
        // Implementar o método para salvar o texto no banco
    }

    public boolean analyzeText(String text){
        try {
            try (LanguageServiceClient language = LanguageServiceClient.create()) {
                Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();

                // Corrigindo o nome da classe para AnalyzeSyntaxResponse
                AnalyzeSyntaxResponse response = language.analyzeSyntax(doc);

                for (Token token : response.getTokensList()) {
                    if (token.getPartOfSpeech().getTag() == PartOfSpeech.Tag.UNKNOWN) {
                        return false; // Retorna falso se encontrar um token desconhecido
                    }
                }

                return true; // Se não houver erros de sintaxe
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
