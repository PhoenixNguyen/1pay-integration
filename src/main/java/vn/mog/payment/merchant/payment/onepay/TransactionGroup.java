package vn.mog.payment.merchant.payment.onepay;

import java.util.List;

public class TransactionGroup {
	private String label;
	private String target;
	private List<GroupParam> params;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<GroupParam> getParams() {
		return params;
	}

	public void setParams(List<GroupParam> params) {
		this.params = params;
	}
}
