package br.com.tech.challenge.videostorageservice.dataprovider;

import br.com.tech.challenge.videostorageservice.dataprovider.dto.CreateVideoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "VideoManagementServiceClient", url = "${videomanagementservice.url}/video")
public interface VideoManagementServiceClient {

    @PostMapping
    void createVideo(CreateVideoDto dto);
}
