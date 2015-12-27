package vn.mog.payment.merchant.payment.onepay;

import org.springframework.web.client.RestTemplate;

public class OnePayPayment {
	// URL checkout của 1Pay.vn
	public static String ONE_PAY_URL = "http://gateway.1pay.vn/api/v2/checkout";
	
	// Merchant ID
	public static String MERCHANT_ID = "";
		
	// Merchant access key
	public static String ACCESS_KEY = "wt702lhmdthd50u04h57";
	
	// Merchant secret key
	public static String SECRET_KEY = "n8idb8ntlijqr6qdj4tl7arr3d80n5nw";
	
	public static String createRequestUrl(String country, String request_id, String amount, 
			String content, String language) {
		
		String url = ONE_PAY_URL + "?access_key=$1&country=$2&request_id=$3&amount=$4&content=$5"
				+ "&signature=$6&language=$7";
		url = url.replace("$1", ACCESS_KEY);
		url = url.replace("$2", country);
		url = url.replace("$3", request_id);
		url = url.replace("$4", amount);
		url = url.replace("$5", content);
		url = url.replace("$6", generateSignature());
		url = url.replace("$7", language);
		return url;
	}
	
	public static String generateSignature() {
		return "signature";
	}
	
	public static RestTemplate restTemplate = null;
	static{
		restTemplate = new RestTemplate();
	}
}
