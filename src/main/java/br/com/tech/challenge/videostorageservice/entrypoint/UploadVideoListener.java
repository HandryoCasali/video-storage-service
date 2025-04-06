package br.com.tech.challenge.videostorageservice.entrypoint;


import br.com.tech.challenge.videostorageservice.dataprovider.VideoManagementServiceClient;
import br.com.tech.challenge.videostorageservice.usecase.ProcessarUploadUsecase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UploadVideoListener {
    private final ProcessarUploadUsecase usecase;

    @SqsListener(value="${sqs.queue.name}", acknowledgementMode = SqsListenerAcknowledgementMode.ON_SUCCESS)
    public void receiveMessage(Message<String> message) {
        System.out.println("Mensagem de id:"+ message.getHeaders().getId() +" recebida: " + message.getPayload());
        usecase.processar(message.getPayload());
    }
}
