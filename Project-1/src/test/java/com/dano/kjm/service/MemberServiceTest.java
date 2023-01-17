//package com.dano.kjm.service;
//
//import com.dano.kjm.domain.member.dto.request.SignUpDto;
//import com.dano.kjm.domain.member.entity.Member;
//import com.dano.kjm.domain.member.exception.MemberDuplicatedException;
//import com.dano.kjm.domain.member.application.MemberService;
//import com.dano.kjm.domain.member.dao.AuthorityRepository;
//import com.dano.kjm.domain.member.dao.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collection;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.when;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
///**
// * MemberServiceTest.java
// * Class 설명을 작성하세요.
// *
// * @author sgh
// * @since 2022.12.13
// */
//@ExtendWith(MockitoExtension.class)
//class MemberServiceTest {
//
//    @Mock
//    MemberRepository memberRepository;
//    @Mock
//    AuthorityRepository authorityRepository;
//    @Mock
//    PasswordEncoder passwordEncoder;
//    @InjectMocks
//    MemberService target;
//
//    @Test
//    @DisplayName("회원 생성 성공 테스트")
//    public void saveMemberTest(){
//        //given, when
//        target.saveMember(createSignUpDto());
//
//        //then
//        verify(memberRepository, times(1)).save(any(Member.class));
//        verify(authorityRepository, times(1)).saveAll(any(Collection.class));
//    }
//
//    @Test
//    @DisplayName("회원 가입시 중복 회원 예외 발생 테스트")
//    public void membership_duplicate_exception_test(){
//        //given
//        when(memberRepository.findByEmail(any())).thenReturn(Optional.of(new Member()));
//
//        // when
//        MemberDuplicatedException memberDuplicatedException = assertThrows(MemberDuplicatedException.class, () -> {
//            target.saveMember(createSignUpDto());
//        });
//
//        //then
//        assertThat(memberDuplicatedException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
//
//    @Test
//    @DisplayName("회원 정보 조회 실패 테스트")
//    public void member_information_inquiry_success_test(){
//        //given
//
//        //when
//
//
//        //then
//    }
//
//    private SignUpDto createSignUpDto() {
//        SignUpDto dto = new SignUpDto();
//        dto.setEmail("test@naver.com");
//        dto.setUsername("test");
//        dto.setAddress("(29394) 경복궁 30-1");
//        dto.setPassword("password!");
//        dto.setDetailAddress("지하 2층");
//        dto.setPhone("01099938883");
//        return dto;
//    }
//
//}