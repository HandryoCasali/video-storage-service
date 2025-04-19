package br.com.tech.challenge.videostorageservice.usecase;

import br.com.tech.challenge.videostorageservice.entrypoint.dto.DownloadPreSignedUrlDto;
import br.com.tech.challenge.videostorageservice.entrypoint.dto.UploadPreSignedUrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
public class GeneratePreSignerUrlUsecase {

    private final S3Presigner s3Presigner;
    private final String bucketName;

    public GeneratePreSignerUrlUsecase(@Value("${s3.bucket.name}") String bucketName) {
        this.s3Presigner = S3Presigner.create();
        this.bucketName = bucketName;
    }

    public UploadPreSignedUrlDto generateUpload(String userId, String fileName) {
        String videoId = UUID.randomUUID().toString();
        String path = userId+"/videos/"+fileName+"_"+videoId+".mp4";

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .contentType("video/mp4")
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(objectRequest)
                .build();
        String url = s3Presigner.presignPutObject(presignRequest).url().toString();
        return new UploadPreSignedUrlDto(videoId, fileName, path, url);
    }

    public DownloadPreSignedUrlDto generateDownload(String zipPath) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(zipPath)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15)) // URL válida por 15 minutos
                .getObjectRequest(getObjectRequest)
                .build();
        String url = s3Presigner.presignGetObject(presignRequest).url().toString();
        return new DownloadPreSignedUrlDto(url);
    }
}
