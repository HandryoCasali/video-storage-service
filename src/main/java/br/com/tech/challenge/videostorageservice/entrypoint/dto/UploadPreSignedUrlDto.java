package br.com.tech.challenge.videostorageservice.entrypoint.dto;

public record UploadPreSignedUrlDto(
        String videoId,
        String fileName,
        String path,
        String url
) {
}
