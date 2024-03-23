package org.efire.net.dto;

public record LookupMatchingIndividualDTO(String customerNo, String firstName, String middleName, String lastName) {


    @Override
    public String toString() {
        return "LookupMatchingIndividualDTO{" +
                "customerNo='" + customerNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
