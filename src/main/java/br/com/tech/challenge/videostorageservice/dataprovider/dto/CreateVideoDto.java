package br.com.tech.challenge.videostorageservice.dataprovider.dto;

public record CreateVideoDto(
        String usuarioId,
        String videoId,
        String videoFileName,
        String videoUrl
) {
}
