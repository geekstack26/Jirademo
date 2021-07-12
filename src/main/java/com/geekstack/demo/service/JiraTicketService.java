package com.geekstack.demo.service;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geekstack.demo.model.JiraPayload;

@Service
public class JiraTicketService {
	private RestTemplate restTemplate;
	@Value("${jira.user-name}")
	private String username;
	@Value("${jira.secret}")
	private String secret;
	@Value("${jira.base-url}")
	private String baseUrl;
	@Value("${jira.ticket-url}")
	private String ticketCreationUrl;


	@Autowired
	public JiraTicketService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String createJiraTicket(JiraPayload jiraPayload) {
		System.out.println(baseUrl.concat(ticketCreationUrl));
		ResponseEntity<String> response = restTemplate.exchange(baseUrl.concat(ticketCreationUrl), HttpMethod.POST,
				new HttpEntity<JiraPayload>(jiraPayload, getHeaders()), String.class);
		if (response != null) {
			return response.getBody();
		}
		return null;
	}

	public HttpHeaders getHeaders() {
		String auth = username + ":" + secret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		return headers;
	}
}
