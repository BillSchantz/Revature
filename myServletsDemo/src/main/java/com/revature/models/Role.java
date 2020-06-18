package com.revature.models;


/**
 * This class is a Model class. This means that it is intended to simply store data.
 * 
 * Our model classes effectively mirror the corresponding tables.
 * The primary difference is that instead of storing int variables for Foreign Keys,
 * we actually go the extra step to obtain the entire object that the foreign key was
 * referencing.
 * 
 * That extra step is optional, but is a very common and convenient design choice.
 * 
 * We also generally create 2 constructors: One with zero parameters, and one with all parameters.
 * We provide getters/setters, and we also override hashcode(), equals(), and toString().
 * 
 * This particular class relates to the ROLES table.
 */
public class Role {

	private int roleId;
	private String role;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
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
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	
}
