package com.dano.kjm.global.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TempCodeTest.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.11
 */
class TempCodeTest {

    @Test
    void 난수_생성_성공_테스트(){
        //given
        String code = TempCode.createCode(6);

        //when, then
        assertThat(code.length()).isEqualTo(6);
    }
}