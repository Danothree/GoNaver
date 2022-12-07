package com.dano.kjm.dto.response;

import com.dano.kjm.entity.Member;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UpdateMember {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 어긋납니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호는 8~20자리 입니다.")
    private String password;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호는 8~20자리 입니다.")
    private String passwordCheck;

    @NotBlank(message = "폰 번호를 입력하세요.")
    private String phone;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "상세주소를 입력해주세요.")
    private String detailAddress;


    public static UpdateMember createMemberFormDto(Member member) {
        return UpdateMember.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .phone(member.getPhone())
                .address(member.getAddress().getAddress())
                .detailAddress(member.getAddress().getPostalCode())
                .build();
    }
}
