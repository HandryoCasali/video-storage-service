package br.com.tech.challenge.videostorageservice.mapper;

import br.com.tech.challenge.videostorageservice.dataprovider.dto.CreateVideoDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadVideoEventMapper {

    public static CreateVideoDto parseS3Event(String jsonMessage){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonMessage);

            JsonNode records = root.path("Records");
            if (records.isArray() && !records.isEmpty()) {
                JsonNode s3Object = records.get(0).path("s3").path("object");
                String key = s3Object.path("key").asText();

                String url = key;
                String[] parts = key.split("/");
                String userId = parts[0];

                String fileWithId = parts[2].replace(".mp4", "");
                int underscoreIndex = fileWithId.lastIndexOf('_');
                String fileName = fileWithId.substring(0, underscoreIndex);
                String videoId = fileWithId.substring(underscoreIndex + 1);

                return new CreateVideoDto(userId, videoId, fileName, url);
            }
            throw new RuntimeException("Não foi possível converter a mensagem: "+jsonMessage);
        } catch (Exception e){
            throw new RuntimeException("Não foi possível converter a mensagem: "+jsonMessage);
        }
    }
}
