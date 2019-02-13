[![Build Status](https://travis-ci.com/isontheline/detectify-client.svg?branch=master)](https://travis-ci.com/isontheline/detectify-client)

# About detectify-client
An unofficial but powerful JAVA client for Detectify API : Go hack yourself!

# About Detectify
> Detectify performs automated security tests on your web application and databases and scans your assets for vulnerabilities including OWASP Top 10, CORS, Amazon S3 Bucket and DNS misconfigurations. 150+ handpicked ethical hackers contribute security findings that are built into our scanner as automated tests. Their submissions go beyond the known CVE libraries that are not a sufficient test bed for modern application security.

[Learn more about Detectify](https://detectify.com)

# Usage
## Initializing the report client
```
// SECRET_KEY must be an empty string if your don't have received it from your Detectify account :
DetectifyReportClient reportClient = new DetectifyReportClient(API_KEY, SECRET_KEY);
```

## Retrieving last report of the specified scan profile :
```		
DetectifyReport lastReport = reportClient.getLastReportFor(SCAN_PROFILE_TOKEN);
```