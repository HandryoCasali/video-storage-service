package br.com.tech.challenge.videostorageservice.usecase;

import br.com.tech.challenge.videostorageservice.dataprovider.VideoManagementServiceClient;
import br.com.tech.challenge.videostorageservice.dataprovider.dto.CreateVideoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProcessarUploadUsecaseTest {
    @Mock
    private VideoManagementServiceClient client;
    @InjectMocks
    private ProcessarUploadUsecase usecase;

    @Test
    void testProcessar(){
        CreateVideoDto dto = new CreateVideoDto("usuarioId", "videoId", "videoFileName", "videoUrl");
        usecase.processar(dto);
        verify(client, times(1)).createVideo(dto);
    }
}