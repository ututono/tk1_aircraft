package model;

import java.util.Objects;

/**
 * @author ZhuoY
 *
 */
public class User {
	
	private String username;
	private int age;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, int age) {
		this.username=username;
		this.age=age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return age == other.age && Objects.equals(username, other.username);
	}
	
	
	
	

}
