package com.centime.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.centime.exception.CentimeException;
import com.centime.modal.User;

@Service("ClientService")
public class ClientService {
	
	@Value("${servicetwourl}")
	private String serviceTwoUrl;

	@Value("${servicethreeurl}")
	private String serviceThreeUrl;

	@Autowired
	@Qualifier("RestTemplate")
	private RestTemplate restTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(ClientService.class);

	public String callServiceTwo() throws CentimeException {
		try {
			return restTemplate.getForEntity(serviceTwoUrl, String.class).getBody();
		} catch (HttpClientErrorException e) {
			
			logger.error("Bad Request while calling service two: [{}]", e.getResponseBodyAsString());
			throw new CentimeException(e, "Bad Request while calling service two");
			
		} catch (HttpServerErrorException e) {
			
			logger.error("Internal Server Error while calling service two: [{}]", e.getResponseBodyAsString());
			throw new CentimeException(e, "Internal Server Error while calling service two:");
			
		} catch (Exception e) {
			logger.error("Unable to reach Service two: [{}]", e);
			throw new CentimeException(e, "Unable to reach Service two");
		}

	}


	public String callServiceThree(final User user) throws CentimeException {
		try {
			return restTemplate.postForEntity(serviceThreeUrl, user, String.class).getBody();

		} catch (HttpClientErrorException e) {
			
			logger.error("Bad Request while calling service three: [{}]", e.getResponseBodyAsString());
			throw new CentimeException(e, "Bad Request while calling service three");
			
		} catch (HttpServerErrorException e) {
			
			logger.error("Internal Server Error while calling service three: [{}]", e.getResponseBodyAsString());
			throw new CentimeException(e, "Internal Server Error while calling service three:");
			
		} catch (Exception e) {
			logger.error("Unable to reach Service three: [{}]", e);
			throw new CentimeException(e, "Unable to reach Service three");
		}


	}


}
