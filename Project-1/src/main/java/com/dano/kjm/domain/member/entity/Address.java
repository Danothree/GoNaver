package com.dano.kjm.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Access(AccessType.FIELD)
@Embeddable
@Getter
@NoArgsConstructor
public class Address implements Serializable {

    private String address;
    private String postalCode;
    private String detail;

    public static Address create(String address, String detail){
        Address result = new Address();
        result.address = getAddress(address);
        result.postalCode = getPostalCode(address);
        result.detail = detail;
        return result;
    }

    private static String getPostalCode(String address) {
        return address.substring(1, address.lastIndexOf(')'));
    }

    private static String getAddress(String address) {
        return address.substring(address.lastIndexOf(')') + 1);
    }

    public String getAddress(){
        StringBuilder sb = new StringBuilder();
        sb.append("(")
                .append(postalCode)
                .append(") ")
                .append(address);
        return sb.toString();
    }
}
