package com.revature.models;

public class Interest {

	private int interestId;
	private String interestName;
	private double interestAmount;
	public Interest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Interest(int interestId, String interestName, double interestAmount) {
		super();
		this.interestId = interestId;
		this.interestName = interestName;
		this.interestAmount = interestAmount;
	}
	public int getInterestId() {
		return interestId;
	}
	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}
	public String getInterestName() {
		return interestName;
	}
	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}
	public double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(interestAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + interestId;
		result = prime * result + ((interestName == null) ? 0 : interestName.hashCode());
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
		Interest other = (Interest) obj;
		if (Double.doubleToLongBits(interestAmount) != Double.doubleToLongBits(other.interestAmount))
			return false;
		if (interestId != other.interestId)
			return false;
		if (interestName == null) {
			if (other.interestName != null)
				return false;
		} else if (!interestName.equals(other.interestName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Interest [interestId=" + interestId + ", interestName=" + interestName + ", interestAmount="
				+ interestAmount + "]";
	}
	
	
}
