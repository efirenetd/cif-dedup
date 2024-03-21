package org.efire.net.dto;

public class CustomerDTO {

    private final String customerNo;
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public CustomerDTO(String customerNo, String firstName, String middleName, String lastName) {
        this.customerNo = customerNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerNo='" + customerNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
