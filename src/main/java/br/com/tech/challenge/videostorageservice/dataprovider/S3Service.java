package br.com.tech.challenge.videostorageservice.dataprovider;

import br.com.tech.challenge.videostorageservice.dataprovider.dto.PreSignedUrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Presigner s3Presigner;
    private final String bucketName;

    public S3Service(@Value("${s3.bucket.name}") String bucketName) {
        this.s3Presigner = S3Presigner.create();
        this.bucketName = bucketName;
    }

    public PreSignedUrlDto generatePresignedUrl(String userId, String fileName) {
        String uuid = UUID.randomUUID().toString();
        String path = userId+"/videos/"+fileName+"_"+uuid+".mp4";

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
        return new PreSignedUrlDto(fileName, path, url);
    }
}
