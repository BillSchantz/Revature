package com.revature.models;

public class TransactionType {

	public int transactionTypeId;
	public String transactionType;
	
	public TransactionType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionType(int transactionTypeId, String transactionType) {
		super();
		this.transactionTypeId = transactionTypeId;
		this.transactionType = transactionType;
	}
	public int getTransactionTypeId() {
		return transactionTypeId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + transactionTypeId;
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
		TransactionType other = (TransactionType) obj;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (transactionTypeId != other.transactionTypeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransactionType [transactionTypeId=" + transactionTypeId + ", transactionType=" + transactionType + "]";
	}
	
	
}
