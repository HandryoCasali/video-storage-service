package br.com.tech.challenge.videostorageservice.dataprovider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "VideoManagementServiceClient", url = "${videomanagementservice.url}")
public interface VideoManagementServiceClient {

    @PostMapping
    void createVideo();
}
