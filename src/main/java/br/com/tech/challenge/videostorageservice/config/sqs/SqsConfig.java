package br.com.tech.challenge.videostorageservice.config.sqs;

import br.com.tech.challenge.videostorageservice.config.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@RequiredArgsConstructor
@Configuration
public class SqsConfig {

    private final AwsProperties awsProperties;

//    @Bean
//    public SqsClient sqsClientLocal() {
//        return SqsClient.builder()
//                .region(Region.US_EAST_1)
//                .credentialsProvider(DefaultCredentialsProvider.create())
//                .build();
//    }

    @Bean
    public SqsClient sqsClientLocal() {
        return SqsClient.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsSessionCredentials.create(
                                awsProperties.getAccessKey(),
                                awsProperties.getSecretKey(),
                                awsProperties.getSessionToken()
                        )
                ))
                .build();
    }
}