package io.isontheline.detectify.client;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.isontheline.detectify.dto.DetectifyProfile;
import io.isontheline.detectify.dto.DetectifyScanStatus;
import io.isontheline.detectify.response.DetectifyAPIResponse;

public class DetectifyProfileClient extends DetectifyCoreClient {
	public DetectifyProfileClient(String apiKey, String secretKey) {
        super(apiKey, secretKey);
    }

    public void startScan(String scanProfile) {
    	String path = String.format("/scans/%s/", scanProfile);

    	this.getAPIResponseFrom("POST", path);
    }

    public DetectifyScanStatus getScanStatus(String scanProfile) {
    	String path = String.format("/scans/%s/", scanProfile);

    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("GET", path);
    	String jsonResponse = apiResponse.getResponse();

    	ObjectMapper om = new ObjectMapper();
    	DetectifyScanStatus scanStatus = null;

    	try {
    		scanStatus = om.readValue(jsonResponse, DetectifyScanStatus.class);

    	} catch(Exception ex) {
    		throw new RuntimeException(ex);
    	}

    	return scanStatus;
    }

    public ArrayList<DetectifyProfile> listProfiles() {
    	String path = "/profiles/";

    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("GET", path);
    	String jsonResponse = apiResponse.getResponse();

    	ObjectMapper om = new ObjectMapper();
    	ArrayList<DetectifyProfile> profilesList = null;

    	try {
    		profilesList = om.readValue(jsonResponse, new TypeReference<ArrayList<DetectifyProfile>>() {});

    	} catch(Exception ex) {
    		throw new RuntimeException(ex);
    	}

    	return profilesList;
    }
}