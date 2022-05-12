
public class Guest {
	String lastName;
	String firstName;
	String email;
	String phoneNumber;

	Guest() {
		this.lastName = "";
		this.firstName = "";
		this.email = "";
		this.phoneNumber = "";
	}

	String getLastName() {
		return lastName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	String getFirstName() {
		return firstName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	String getPhoneNumber() {
		return phoneNumber;
	}

	void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	boolean equals(Object obj, int modAuth) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Guest other = (Guest) obj;
		switch (modAuth) {
		case 1:
			return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
		case 2:
			return this.email.equals(other.email);
		case 3:
			return this.phoneNumber.equals(other.phoneNumber);
		}
		return false;
	}

}
