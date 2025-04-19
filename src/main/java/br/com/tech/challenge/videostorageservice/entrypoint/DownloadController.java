package br.com.tech.challenge.videostorageservice.entrypoint;

import br.com.tech.challenge.videostorageservice.entrypoint.dto.DownloadPreSignedUrlDto;
import br.com.tech.challenge.videostorageservice.entrypoint.dto.DownloadRequestDto;
import br.com.tech.challenge.videostorageservice.usecase.GeneratePreSignerUrlUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/download")
public class DownloadController {

    private final GeneratePreSignerUrlUsecase s3Service;

    // 1️⃣ Gerar pre-signed URLs para download do zip
    @PostMapping("/preSignedUrls")
    public ResponseEntity<DownloadPreSignedUrlDto> generateUploadUrls(@RequestBody DownloadRequestDto request) {
        var dto = s3Service.generateDownload(request.zipPath());
        return ResponseEntity.ok(dto);
    }

}
