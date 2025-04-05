package br.com.tech.challenge.videostorageservice.dataprovider.dto;

public record PreSignedUrlDto(
        String fileName,
        String path,
        String url
) {
}
