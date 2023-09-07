package util;

import officeLunch.Office.Lunch.request.BkashPaymentRequest;
import officeLunch.Office.Lunch.response.BkashPaymentResponse;
import officeLunch.Office.Lunch.response.CommonResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class UtilService {
    private static int counter = 0;
    private final RestTemplate restTemplate;

    public UtilService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CommonResponse callApi(String baseUrl, String path, BkashPaymentRequest bkashPaymentRequest){
        long stratTime = System.currentTimeMillis();
        String url = path == null ? baseUrl : baseUrl + "/" + path;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BkashPaymentRequest> request = new HttpEntity<>(bkashPaymentRequest, httpHeaders);
        try {
            ResponseEntity<String> bkashPaymentResponse = restTemplate.exchange(url, HttpMethod.POST,
                    request, String.class);
            if (bkashPaymentResponse.getStatusCodeValue() == HttpStatus.OK.value() ||
                    bkashPaymentResponse.getBody() != null){
                return CommonResponse.builder()
                        .hasError(false)
                        .message("API CALLING SUCCESSFUL")
                        .content(bkashPaymentResponse.getBody().toString()).build();
            }
            else {
                return this.failedApiCallingResponse();
            }
        }
        catch (RestClientException restClientException){
            restClientException.printStackTrace();
            return this.failedApiCallingResponse();
        }
    }
    public UtilResponse generateTxnAndRefId(Modules modules, Modules anotherModule){
        LocalDateTime dateToday = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyymmdd");
        String timeStamp = dateToday.format(dateTimeFormatter);
        counter = counter +1 %10000;
        String number = String.format("2%d", counter);
        String txnId = modules+ timeStamp+ number;
        String refId = anotherModule + timeStamp + number;
        return  UtilResponse.builder()
                .TxnId(txnId)
                .referenceId(refId).build();
    }
    public static CommonResponse universalFailedResponse(){
        return CommonResponse.builder()
                .hasError(true)
                .message("Something went wrong! Please call support.")
                .content(null).build();
    }
    private CommonResponse failedApiCallingResponse(){
        return CommonResponse.builder()
                .hasError(true)
                .message("API CALLING FAILED")
                .content(null).build();
    }
}
