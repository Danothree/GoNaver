package com.dano.kjm.dto.response;

import com.dano.kjm.entity.Member;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class MemberDetail {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 어긋납니다.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

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

    // 1) MemberDetail 에 비밀번호가 같이 가는 것은 좋지 않음, 애초에 이 값은 PasswordEncoder 로 암호화된 값이 반환됨
    public static MemberDetail createMemberFormDto(Member member) {
        return MemberDetail.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .password(member.getPassword())
                .phone(member.getPhone())
                .address(member.getAddress().getAddress())
                .detailAddress(member.getAddress().getPostalCode())
                .build();
    }
}
