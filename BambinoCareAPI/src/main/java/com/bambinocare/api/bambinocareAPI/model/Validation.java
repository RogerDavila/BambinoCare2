package com.bambinocare.api.bambinocareAPI.model;

public class Validation {

	public String result;
	public boolean requireOtherView;
	public String otherView;
	
	public Validation(String result, boolean requireOtherView, String otherView) {
		this.result = result;
		this.requireOtherView = requireOtherView;
		this.otherView = otherView;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public boolean isRequireOtherView() {
		return requireOtherView;
	}
	public void setRequireOtherView(boolean requireOtherView) {
		this.requireOtherView = requireOtherView;
	}
	public String getOtherView() {
		return otherView;
	}
	public void setOtherView(String otherView) {
		this.otherView = otherView;
	}
}
