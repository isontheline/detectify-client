package io.isontheline.detectify.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import io.isontheline.detectify.client.DetectifyProfileClient;
import io.isontheline.detectify.response.DetectifyAPIResponse;

public class DetectifyCoreClient {
    /**
     * Detectify API endpoint, no trailing slash
     */
    protected static final String Endpoint = "https://api.detectify.com/rest/v2";

    private String apiKey;
    private String secretKey;

    public DetectifyCoreClient(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    /**
     * Create the HTTP headers for API requests.
     *
     * @param method    The HTTP method to use for the request, in uppercase
     * @param path      The path of the request
     * @param timestamp The timestamp of the request
     * @param body      The optional body of the request
     */
    protected Map<String, String> MakeHeaders(String method, String path, Date timestamp, String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Detectify-Key", this.apiKey);
        
        // If secret key is set, making the request signature :
        if(this.secretKey != null && !"".equals(this.secretKey)) {
        	// Signature timestamp uses Unix epoch time
            Long epoch = timestamp.getTime() / 1000;

            // Format hash payload
            String message = String.format("%s;%s;%s;%s;%s", method, path, this.apiKey, epoch, body);
        	
        	// Decode base64 secret key to binary
            byte[] key = Base64.getDecoder().decode(this.secretKey);

            // Create the signature
            String signature = "";
            try {
                Mac hasher = Mac.getInstance("HmacSHA256");
                hasher.init(new SecretKeySpec(key, "HmacSHA256"));

                byte[] hash = hasher.doFinal(message.getBytes());

                // Encode signature back to base64
                signature = Base64.getEncoder().encodeToString(hash);
            } catch (NoSuchAlgorithmException ex) {
            	throw new RuntimeException(ex);
            	
            } catch (InvalidKeyException ex) {
            	throw new RuntimeException(ex);
            }

            headers.put("X-Detectify-Signature", signature);
            headers.put("X-Detectify-Timestamp", epoch.toString());
        }

        return headers;
    }
    
    protected DetectifyAPIResponse getAPIResponseFrom(String method, String path) {
		DetectifyAPIResponse apiResponse = new DetectifyAPIResponse();
		apiResponse.setStatusCode(400);
		apiResponse.setResponse("UNKNOWN");
		
		Date timestamp = new Date();
		URL url = null;
		
        try {
            url = new URL(String.format("%s%s", DetectifyProfileClient.Endpoint, path));
        } catch (IOException ex) {
        	apiResponse.setResponse(ex.getMessage());
        	
        	return apiResponse;
        }

        // Create Detectify signature HTTP headers
        Map<String, String> headers = MakeHeaders(method, path, timestamp, "");

        int statusCode = 0;

        // Buffer for JSON response
        StringBuffer response = new StringBuffer();

        // Call the Detectify API
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }

            // Get result of request
            statusCode = conn.getResponseCode();

            // Read JSON response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            
            in.close();
            conn.disconnect();
            
        } catch (IOException ex) {
        	apiResponse.setStatusCode(statusCode);
        	apiResponse.setResponse(ex.getMessage());
        	
        	if(statusCode != 202 && statusCode != 409 && statusCode != 423) {
        		return apiResponse;
        	}
        }
        
        apiResponse.setStatusCode(statusCode);

        switch (statusCode) {
            case 200:
            	apiResponse.setResponse(response.toString());
                break;
            case 202: // Used by DetectifyProfileClient
            	apiResponse.setResponse("Scan start request accepted");
                break;
            case 400:
            	apiResponse.setResponse("Invalid scan profile token");
                break;
            case 401:
            	apiResponse.setResponse("Missing/invalid API key or message signature, or invalid timestamp");
                break;
            case 403:
            	apiResponse.setResponse("The API key cannot access this functionality");
                break;
            case 404:
            	apiResponse.setResponse("No scan running for the specified profile, or the specified scan profile does not exist or the API key cannot access the scan profile");
            	break;
            case 409: // Used by DetectifyProfileClient
            	apiResponse.setResponse("A scan is already running on the specified profile");
                break;
            case 423: // Used by DetectifyProfileClient
            	apiResponse.setResponse("The domain is not verified");
                break;
            case 500:
            	apiResponse.setResponse("An error occurred while processing the request");
                break;
            case 503:
            	apiResponse.setResponse("An error occurred while processing the request");
                break;
            default:
            	apiResponse.setResponse(String.format("Unhandled API response code: %d", statusCode));
        }
        
        return apiResponse;
	}
}