package com.dano.kjm.global.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SellerServiceEventListener {

    @EventListener
    public void sign(SellerEvent sellerEvent) {
        log.info("{}님께서 판매자 권한을 획득하셨습니다.",sellerEvent.getName());
    }
}
