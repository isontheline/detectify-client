package io.isontheline.detectify.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import io.isontheline.detectify.dto.DetectifyReport;

public class DetectifyReportClientTest {
	@Test
	public void launchTest() {
		String apiKey = System.getProperty("apiKey");
		String secretKey = System.getProperty("secretKey");
		String scanProfileToken = System.getProperty("scanProfileToken");
		
		// Skip tests if we don't have API key
		if(apiKey == null && secretKey == null) {
			return;
		}
		
		DetectifyReportClient drc = new DetectifyReportClient(apiKey, secretKey);
		DetectifyReport report = drc.getLastReportFor(scanProfileToken);
		
		assertNotNull(report);
	}
}
