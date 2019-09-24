# About detectify-client
An unofficial but powerful JAVA client for Detectify API : Go hack yourself!

# About Detectify
> Detectify performs automated security tests on your web application and databases and scans your assets for vulnerabilities including OWASP Top 10, CORS, Amazon S3 Bucket and DNS misconfigurations. 150+ handpicked ethical hackers contribute security findings that are built into our scanner as automated tests. Their submissions go beyond the known CVE libraries that are not a sufficient test bed for modern application security.

[Learn more about Detectify](https://detectify.com)

# Usage
## Retrieving last report of the specified scan profile :
```
// SECRET_KEY must be an empty string if you don't have received it from your Detectify account :
DetectifyReportClient reportClient = new DetectifyReportClient(API_KEY, SECRET_KEY);

DetectifyReport lastReport = reportClient.getLastReportFor(SCAN_PROFILE_TOKEN);
Double cvss = lastReport.getCvss();
```

## Retrieving all domains :
```
// SECRET_KEY must be an empty string if you don't have received it from your Detectify account :
DetectifyDomainClient domainClient = new DetectifyDomainClient(API_KEY, SECRET_KEY);

ArrayList<DetectifyDomain> domainClient = domainClient.listDomains();
```