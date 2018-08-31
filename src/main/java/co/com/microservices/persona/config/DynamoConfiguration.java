package co.com.microservices.persona.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories(basePackages="co.com.microservices.persona.repository")
public class DynamoConfiguration {
	@Value("${amazon.dynamodb.endpoint}")
	private String dbEndpoint;
	
	@Value("${amazon.aws.acceskey}")
	private String accessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String secretKey;
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		
		AmazonDynamoDB dynamoDb = new AmazonDynamoDBClient(amazonAWSCredentialas());
		if(!StringUtils.isEmpty(dbEndpoint)) {
			dynamoDb.setEndpoint(dbEndpoint);
		}
		
		return dynamoDb;
	}
	
	@Bean
	public AWSCredentials amazonAWSCredentialas() {
		return new BasicAWSCredentials(accessKey, secretKey);	
	}
}
