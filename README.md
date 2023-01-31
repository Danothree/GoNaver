# GoNaver

**개선점**

- DTO가 많아도 좋으니 상황에 맞게 생성
- 변수명 의미에 맞게 지정
- 코드 정리

**TO DO**
- header css 고치기 - 완료
- 회원가입 js 전체적인 체크 고치기 - 완료
- 회원수정 페이지 및 구현
- 회원삭제 구현
- 로그인실패 에러처리 구현

---
## 기획


### 기능
- 판매자 신청
  - api : POST /seller (핸드폰문자로 인증받기 구현)
  - table : (apply_seller_log) memberId, confirm, insertDt 
- 비밀번호 변경
- 판매자 페이지(메인페이지 현황)
    - 판매자
        - table : (seller) sellerId, money, 
        - table : (seller_item) sellerItemId, sellerId, itemId
    - 상품 CRUD
      - api : POST /items
      - table : (item) itemId, name, price, itemStatus, (List)categoryItem
      - table : (categoryItem) categoryItemId, itemId, categoryId
      - table : (category) categoryId, type
    - 판매 목록 => 카테고리, 조회
      - api : GET /seller/items/{page}
    - 판매 총금액
    - 입출금
    - 구매 요청 현황
      - 목록 조회
      - 배송 상태 변경
      - 배송 취소 -> 메세지
- 상품
  - 목록
  - 카테고리, 금액, 상품명으로 조회 (페이징)
  - 상품 상세페이지
      - api : GET /items/{page}
    - 개수, 장바구니, 구매
    - 상품평, 문의
  - 상품 판매등록
    - 이미지, 이름, 가격, 상세정보, 수량, size, 카테고리
- 장바구니
    - 상품 선택 및 개수 변경
    - 상품 삭제
    - 주문
    - 선택된 상품 총 금액
    - 이전 페이지 이동
- 주문
  - 배송지 선택
  - 결제 방법
  - 결제 하기
  - 할인
  - 취소
- 관리자
  - 


---
## Redis Docker 실행
```
docker pull redis
docker run -d -p 6379:6379  --hostname redis --name radis redis
```

참조 : [Docker Redis 공식문서](https://hub.docker.com/_/redis/)

### * yaml 구성 추가
```yaml
spring:
  session:
    store-type: redis
    redis:
      namespace: spring:session #default
  redis:
    host: localhost
    port: 6379

```