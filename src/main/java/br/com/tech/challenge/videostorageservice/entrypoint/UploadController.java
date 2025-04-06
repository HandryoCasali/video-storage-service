package br.com.tech.challenge.videostorageservice.entrypoint;

import br.com.tech.challenge.videostorageservice.usecase.GeneratePreSignerUrlUsecase;
import br.com.tech.challenge.videostorageservice.dataprovider.dto.PreSignedUrlDto;
import br.com.tech.challenge.videostorageservice.entrypoint.dto.UploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final GeneratePreSignerUrlUsecase s3Service;

    // 1️⃣ Gerar pre-signed URLs para múltiplos vídeos
    @PostMapping("/preSignedUrls")
    public ResponseEntity<List<PreSignedUrlDto>> generateUploadUrls(@RequestHeader String userId, @RequestBody UploadRequestDto request) {
        List<PreSignedUrlDto> dtos = request.fileNames()
                .stream()
                    .map(filename-> s3Service.generate(userId, filename))
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
