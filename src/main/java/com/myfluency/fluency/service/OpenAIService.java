package com.myfluency.fluency.service;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private static final String API_KEY = "sua-chave-api-aqui";

    public String getResponse(String prompt) throws Exception {
        OkHttpClient client = new OkHttpClient();

        JsonObject json = new JsonObject();
        json.addProperty("model", "gpt-3.5-turbo");
        json.addProperty("prompt", prompt);
        json.addProperty("max_tokens", 150);

        RequestBody body = RequestBody.create(
                json.toString(), okhttp3.MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new Exception("Error: " + response.message());
            }
        }
    }
}
