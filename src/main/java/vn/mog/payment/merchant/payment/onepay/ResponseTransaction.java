package vn.mog.payment.merchant.payment.onepay;

import java.util.List;

public class ResponseTransaction {
	private String transaction;
	private String code;
	private List<Group> groups;
	
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public class Group {
		private String label;
		private String target;
		private List<Param> params;
		
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

		public List<Param> getParams() {
			return params;
		}

		public void setParams(List<Param> params) {
			this.params = params;
		}

		
		public class Param {
			private String name;
			private String type;
			private String label;
			private List<Option> options;
			
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

			public List<Option> getOptions() {
				return options;
			}

			public void setOptions(List<Option> options) {
				this.options = options;
			}

			public class Option {
				private String label;
				private String value;
				
				public String getLabel() {
					return label;
				}
				public void setLabel(String label) {
					this.label = label;
				}
				public String getValue() {
					return value;
				}
				public void setValue(String value) {
					this.value = value;
				}
			}
		}
	}
}
