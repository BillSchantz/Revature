package com.revature.models;

public class Account {
	private int AccountId;
	private double balance;
	private int statusId;
	private int typeId;
	private String nickname;
	private int ownerId;

	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, double balance, int statusId, int typeId, String nickname, int ownerId) {
		super();
		AccountId = accountId;
		this.balance = balance;
		this.statusId = statusId;
		this.typeId = typeId;
		this.nickname = nickname;
		this.ownerId = ownerId;
	}
	public int getAccountId() {
		return AccountId;
	}
	public void setAccountId(int accountId) {
		AccountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + AccountId;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + statusId;
		result = prime * result + typeId;
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
		Account other = (Account) obj;
		if (AccountId != other.AccountId)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (statusId != other.statusId)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [AccountId=" + AccountId + ", balance=" + balance + ", statusId=" + statusId + ", typeId="
				+ typeId + ", nickname=" + nickname + "]";
	}
	
	
	
}
