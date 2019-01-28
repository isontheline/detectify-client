package io.isontheline.detectify.client;
import io.isontheline.detectify.response.DetectifyAPIResponse;

public class DetectifyProfileClient extends DetectifyCoreClient {
	public DetectifyProfileClient(String apiKey, String secretKey) {
        super(apiKey, secretKey);
    }
	
    public void startScan(String scanProfile) {
    	String path = String.format("/scans/%s/", scanProfile);
    	
    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("POST", path);
    	
    	System.out.println(apiResponse.getResponse());
    }

    public void getScanStatus(String scanProfile) {
    	String path = String.format("/scans/%s/", scanProfile);
    	
    	DetectifyAPIResponse apiResponse = this.getAPIResponseFrom("GET", path);
    	
    	System.out.println(apiResponse.getResponse());
    }
}