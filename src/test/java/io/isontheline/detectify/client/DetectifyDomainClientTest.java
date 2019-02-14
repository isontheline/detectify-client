package io.isontheline.detectify.client;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import io.isontheline.detectify.dto.DetectifyDomain;

public class DetectifyDomainClientTest {
	@Test
	public void launchTest() {
		String apiKey = System.getProperty("apiKey");
		String secretKey = System.getProperty("secretKey");
		
		// Skip tests if we don't have API key
		if(apiKey == null && secretKey == null) {
			return;
		}
		
		DetectifyDomainClient ddc = new DetectifyDomainClient(apiKey, secretKey);
		ArrayList<DetectifyDomain> domainsList = ddc.listDomains();
		
		assertNotNull(domainsList);
	}
}
