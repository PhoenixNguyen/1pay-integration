package vn.mog.payment.merchant.payment.onepay;

import java.util.List;

public class ResponseTransaction {
	private String transaction;
	private String code;
	private List<TransactionGroup> groups;
	
	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TransactionGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TransactionGroup> groups) {
		this.groups = groups;
	}

}
