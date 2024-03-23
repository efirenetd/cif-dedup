package org.efire.net.dto;

public record CustomerDTO(String customerNo, String firstName, String middleName, String lastName) {

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
