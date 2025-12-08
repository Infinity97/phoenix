package com.pheonix.core.utils.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.pheonix.core.utils.constants.PropertyConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@Slf4j
public class AmazonConfig {

	@Autowired
	private Environment environment;

	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials awsCredentials =
			new BasicAWSCredentials(Objects.requireNonNull(environment.getProperty(PropertyConstants.AWS_ACCESS_KEY)),
				Objects.requireNonNull(environment.getProperty(PropertyConstants.AWS_SECRET_KEY)));
		return AmazonS3ClientBuilder
			.standard()
			.withRegion(Objects.requireNonNull(environment.getProperty(PropertyConstants.AWS_REGION_NAME)))
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
			.build();
	}
}
