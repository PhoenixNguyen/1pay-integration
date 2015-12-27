package vn.mog.payment.merchant.payment.onepay;

import java.util.List;

public class GroupParam {
	private String name;
	private String type;
	private String label;
	private List<ParamOption> options;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ParamOption> getOptions() {
		return options;
	}

	public void setOptions(List<ParamOption> options) {
		this.options = options;
	}
}
