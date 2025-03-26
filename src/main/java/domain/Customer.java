package domain;

import java.util.Objects;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;


public class Customer {

	//@NotNull(message = "ID must be provided.")
	//@Min(message = "ID must be greater than 100.", value = 100)
	private Integer customerId;

	@NotNull(message = "Username must be provided.")
	@NotBlank(message = "Username must be provided.")
	@Length(min = 5, message = "Username must contain at least five characters.")
	private String username;

	@NotNull(message = "First name must be provided.")
	@NotBlank(message = "First name must be provided.")
	private String firstName;

	@NotNull(message = "Last name must be provided.")
	@NotBlank(message = "Last name must be provided.")
	private String surname;

	@NotNull(message = "Email must be provided.")
	@NotBlank(message = "Email must be provided.")
	@Length(min = 5, message = "Password must contain at least five characters.")
	private String password;

	@NotNull(message = "Email must be provided.")
	@NotBlank(message = "Email must be provided.")
	private String emailAddress;

	@NotNull(message = "Address must be provided.")
	@NotBlank(message = "Address must be provided.")
	private String shippingAddress;

	public Customer() {
	}

	public Customer(String username, String firstName, String surname, String password, String shippingAddress, String emailAddress) {
		this.username = username;
		this.firstName = firstName;
		this.surname = surname;
		this.shippingAddress = shippingAddress;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer personId) {
		this.customerId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Customer{" + "customerId=" + customerId + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 31 * hash + Objects.hashCode(this.username);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Customer other = (Customer) obj;
		return Objects.equals(this.username, other.username);
	}


}
