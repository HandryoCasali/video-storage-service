package br.com.tech.challenge.videostorageservice.config.sqs;


import br.com.tech.challenge.videostorageservice.dataprovider.VideoManagementServiceClient;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UploadVideoListener {
    private final VideoManagementServiceClient client;


    @SqsListener(value="${sqs.queue.name}", acknowledgementMode = SqsListenerAcknowledgementMode.ON_SUCCESS)
    public void receiveMessage(Message<String> message) {
        System.out.println("Mensagem recebida: " + message);
        System.out.println("Mensagem recebida: " + message.getPayload());
        // Chama lógica para criar o video
//        client.createVideo();
    }
}
