package com.dano.kjm.dto;

import com.dano.kjm.constant.Role;
import com.dano.kjm.entity.Address;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberFormDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 어긋납니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호는 8~20자리 입니다.")
    private String password;

    @NotBlank(message = "폰 번호를 입력하세요.")
    private String phone;

    @NotEmpty(message = "판매자, 구매자 중 하나를 선택해주세요.")
    private String role;

    @NotEmpty(message = "주소를 입력하세요.")
    private String city;

    @NotEmpty(message = "주소를 입력하세요.")
    private String street;

    @NotEmpty(message = "주소를 입력하세요.")
    private String postalCode;
}
