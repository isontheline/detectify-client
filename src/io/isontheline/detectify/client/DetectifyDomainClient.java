package io.isontheline.detectify.client;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.isontheline.detectify.dto.DetectifyDomain;
import io.isontheline.detectify.response.DetectifyAPIResponse;

public class DetectifyDomainClient extends DetectifyCoreClient {
	public DetectifyDomainClient(String apiKey, String secretKey) {
        super(apiKey, secretKey);
    }
	
    public ArrayList<DetectifyDomain> listDomains() {
    	String path = String.format("/domains/");
    	
    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("GET", path);
    	String jsonResponse = apiResponse.getResponse();
    	
    	ObjectMapper om = new ObjectMapper();
    	ArrayList<DetectifyDomain> domainsList = null;
    	
    	try {
    		domainsList = om.readValue(jsonResponse, new TypeReference<ArrayList<DetectifyDomain>>() {});
    		
    	} catch(Exception ex) {
    		throw new RuntimeException(ex);
    	}

    	return domainsList;
    }
}