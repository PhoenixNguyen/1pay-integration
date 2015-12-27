package vn.mog.payment.merchant.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.mog.payment.merchant.payment.onepay.OnePayPayment;
import vn.mog.payment.merchant.payment.onepay.ResponseTransaction;

@Controller
public class PaymentController {

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public @ResponseBody ResponseTransaction pay(@RequestParam(value = "country", defaultValue = "VN") String country,
			@RequestParam(value = "amount") double amount,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "language", defaultValue = "en") String language){
		
		String request_id = UUID.randomUUID().toString().replace("-", "");
		// TODO Merchant save transaction
		
		// Send request to 1Pay
		String url = OnePayPayment.createRequestUrl(country, request_id, String.valueOf(amount), content, language);
		
		// ResponseEntity<String> response = OnePayPayment.restTemplate.postForEntity(url, null, String.class);
		ResponseTransaction response = OnePayPayment.restTemplate.postForObject(url, null, ResponseTransaction.class);
		
		// TODO Merchant Update transaction
		
		System.out.println("response: " + response.getTransaction());
		
		// Display screen to User choose payment method
		return response;
	}
	
	@RequestMapping(value = "/receiveNotify", method = RequestMethod.POST)
	public ResponseEntity<String> receiveNotify(
			@RequestParam("txnId") String transactionId,
			@RequestParam("orderId") String orderId,
			@RequestParam("resultCode") int resultCode,
			@RequestParam("totalPrice") long totalPrice,
			@RequestParam("checksum") String Checksum,
			@RequestParam(value = "language", defaultValue="vi")String language){
		
		// TODO Update to finish transaction
		return new ResponseEntity<String>("0", HttpStatus.OK);
	}
}
