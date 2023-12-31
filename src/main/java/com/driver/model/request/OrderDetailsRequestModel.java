package com.driver.model.request;

public class OrderDetailsRequestModel {

	private String[] items;
	private float cost;
	private String userId;

	public OrderDetailsRequestModel() {
	}

	public OrderDetailsRequestModel(String[] items, float cost, String userId) {
		this.items = items;
		this.cost = cost;
		this.userId = userId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
