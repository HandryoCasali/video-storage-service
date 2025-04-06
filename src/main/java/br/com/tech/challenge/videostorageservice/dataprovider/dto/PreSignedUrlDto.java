package br.com.tech.challenge.videostorageservice.dataprovider.dto;

public record PreSignedUrlDto(
        String videoId,
        String fileName,
        String path,
        String url
) {
}
