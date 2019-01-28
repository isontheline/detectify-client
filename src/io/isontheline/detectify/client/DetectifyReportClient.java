package io.isontheline.detectify.client;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.isontheline.detectify.dto.DetectifyReport;
import io.isontheline.detectify.response.DetectifyAPIResponse;

public class DetectifyReportClient extends DetectifyCoreClient {
	public DetectifyReportClient(String apiKey, String secretKey) {
        super(apiKey, secretKey);
    }
	
    public DetectifyReport getLastReportFor(String scanProfile) {
    	String path = String.format("/reports/%s/latest/", scanProfile);
    	
    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("GET", path);
    	String jsonResponse = apiResponse.getResponse();
    	
    	ObjectMapper om = new ObjectMapper();
    	DetectifyReport report = null;
    	
    	try {
    		report = om.readValue(jsonResponse, DetectifyReport.class);
    		
    	} catch(Exception ex) {
    		throw new RuntimeException(ex);
    	}

    	return report;
    }
}