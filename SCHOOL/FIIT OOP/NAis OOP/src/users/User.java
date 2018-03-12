package users;


public class User {
	private int user_id;
	private String first_name;
	private String last_name;
	private String nickname;
	private String gender;
	private String date_of_birth;
	private String city;
	private String address;
	
	public User(int user_id, String nickname, String first_name, String last_name, int gender, String date_of_birth, String city, String address) {
		this.setUser_id(user_id);
		this.setNickname(nickname);
		this.setFirst_name(first_name);
		this.setLast_name(last_name);
		if (gender == 1) {
			this.setGender("MALE");
		} else if (gender == 2) {
			this.setGender("FEMALE");
		}
		this.setDate_of_birth(date_of_birth);
		this.setCity(city);
		this.setAddress(address);
	}
	
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getDate_of_birth() {
		return date_of_birth;
	}


	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}
