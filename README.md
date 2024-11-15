# 편의점

## 파일 구조

```
📁 main
└──📁 java  
    └──📁 store
         ├──📁 controller
         │   └── MainController
         ├──📁 domain
         │   ├── ConvenienceStore
         │   ├── Order
         │   ├── OrderProducts
         │   ├── Orders
         │   ├── Product
         │   ├── ProductPromotions
         │   ├── Products
         │   ├── Promotion
         │   └── Promotions
         ├──📁 view
         │   ├──📁 enums
         │   │   ├── ErrorMessage
         │   │   ├── InputStatus
         │   │   └── PriceType
         │   ├── InputView
         │   ├── OutputView
         │   ├── ResourceFileReadView
         │   └── DateParser
         └── Application
└──📁 resources
    ├── products.md
    └── promotions.md
    
📁 test
└──📁 java
    └──📁 store
        └── ApplicationTest
```

---

## 기능 목록

### **재고관리**

- [x]  파일에서 데이터 불러오기
- [x]  행사 목록 불러오기
- [x]  상품 목록 불러오기
- [x]  주문 목록과 상품 맵핑

### **프로모션 할인**

- [x]  프로모션 재고 객체 생성
- [x]  입력받은 상품이 프로모션 중인지 확인 (기간 확인)
- [x]  프로모션 재고 확인
    - [x]  프로모션 가능 재고보다 적게 가져온 경우
    - [x]  재고보다 많이 가져온 경우

### **멤버십 할인**

- [x]  멤버십 할인 적용 확인
- [x]  프로모션 적용 상품 제외 가격 할인 (최대 8000원)

### **영수증 출력**

- [x]  구매 상품 내역
- [x]  증정 상품 내역
- [x]  총 구매액
- [x]  행사 할인
- [x]  멤버십 할인
- [x]  최종 결제 금액

### **입출력**

**입력**

- [x]  구매할 상품명, 수량 입력
- [x]  프로모션 재고 관련
- [x]  멤버십 할인 적용
- [x]  추가 구매 여부

**출력**

- [x]  보유 상품 출력

    - 재고가 0개 → 재고 없음
    - 프로모션 상품인데 일반 재고가 없을 경우 → 재고 없음

- [x]  영수증 출력

### **예외**

**[ERROR]로 시작**

- [x]  구매 상품 입력시
    - [x]  형식이 올바르지 않은 경우
    - [x]  재고보다 많이 구매하려는 경우 
    - [x]  재고에 구매하려는 상품명이 없는 경우
- [x]  잘못된 입력일 경우
    - [x]  여부 안내 메세지 `Y` `N` 제외 다른 문자 입력