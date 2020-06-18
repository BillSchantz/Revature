package com.revature.models;

import java.util.Date;

public class Transaction {

	private int transactionId;
	private int userId;
	private int accountId;
	private int transactionTypeId;
	private double amount;
	private double newBalance;
	private Date transactionDate = new Date();
	private int destinationUserId;
	private int destinationAccountId;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transactionId, int userId, int accountId, int transactionTypeId, double amount,
			double newBalance, Date transactionDate, int destinationUserId, int destinationAccountId) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.accountId = accountId;
		this.transactionTypeId = transactionTypeId;
		this.amount = amount;
		this.newBalance = newBalance;
		this.transactionDate = transactionDate;
		this.destinationUserId = destinationUserId;
		this.destinationAccountId = destinationAccountId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(int transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getDestinationUserId() {
		return destinationUserId;
	}

	public void setDestinationUserId(int destinationUserId) {
		this.destinationUserId = destinationUserId;
	}

	public int getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(int destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + destinationAccountId;
		result = prime * result + destinationUserId;
		temp = Double.doubleToLongBits(newBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + transactionId;
		result = prime * result + transactionTypeId;
		result = prime * result + userId;
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
		Transaction other = (Transaction) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (destinationAccountId != other.destinationAccountId)
			return false;
		if (destinationUserId != other.destinationUserId)
			return false;
		if (Double.doubleToLongBits(newBalance) != Double.doubleToLongBits(other.newBalance))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionTypeId != other.transactionTypeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", accountId=" + accountId
				+ ", transactionTypeId=" + transactionTypeId + ", amount=" + amount + ", newBalance=" + newBalance
				+ ", transactionDate=" + transactionDate + ", destinationUserId=" + destinationUserId
				+ ", destinationAccountId=" + destinationAccountId + "]";
	}
	

}
