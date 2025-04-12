package br.com.tech.challenge.videostorageservice.usecase;

import br.com.tech.challenge.videostorageservice.dataprovider.VideoManagementServiceClient;
import br.com.tech.challenge.videostorageservice.dataprovider.dto.CreateVideoDto;
import br.com.tech.challenge.videostorageservice.mapper.UploadVideoEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProcessarUploadUsecase {
    private final VideoManagementServiceClient client;

    public void processar(String mensagem){
        CreateVideoDto dto = UploadVideoEventMapper.parseS3Event(mensagem);
        client.createVideo(dto);
    }
}
