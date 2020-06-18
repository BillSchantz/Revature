package com.revature.models;

public class Fees {

	private int feeId;
	private String feeName;
	private double feeAmount;
	
	public Fees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fees(int feeId, String feeName, double feeAmount) {
		super();
		this.feeId = feeId;
		this.feeName = feeName;
		this.feeAmount = feeAmount;
	}

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(feeAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + feeId;
		result = prime * result + ((feeName == null) ? 0 : feeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fees other = (Fees) obj;
		if (Double.doubleToLongBits(feeAmount) != Double.doubleToLongBits(other.feeAmount))
			return false;
		if (feeId != other.feeId)
			return false;
		if (feeName == null) {
			if (other.feeName != null)
				return false;
		} else if (!feeName.equals(other.feeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fees [feeId=" + feeId + ", feeName=" + feeName + ", feeAmount=" + feeAmount + "]";
	}
	
	
}
