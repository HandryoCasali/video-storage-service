package br.com.tech.challenge.videostorageservice.entrypoint.dto;

import java.util.List;

public record UploadRequestDto(List<String> fileNames) {
}
