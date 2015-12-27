package vn.mog.payment.merchant.controller;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.mog.payment.merchant.payment.OnePayPayment;

@Controller
public class PaymentController {

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public String pay(@RequestParam(value = "country", defaultValue = "VN") String country,
			@RequestParam(value = "amount") double amount,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "country", defaultValue = "en") String language){
		
		String request_id = UUID.randomUUID().toString().replace("-", "");
		// TODO Merchant save transaction
		
		// Send request to 1Pay
		String url = OnePayPayment.createRequestUrl(country, request_id, String.valueOf(amount), content, language);
		
		ResponseEntity<String> response = OnePayPayment.restTemplate.postForEntity(url, null, String.class);
		
		System.out.println("response: " + response);
		// Display screen to choose payment method
		
		return response.getBody();
	}
}
