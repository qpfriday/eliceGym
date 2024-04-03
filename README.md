# 주요 사항

## 1. 프로젝트 개요
- frontend: vue.js
- backend: Spring Boot
- database: mariaDB

## 2. 초기 셋팅
1. setting > build > gradle 에서 Run -> intelliJ 로 변경
![화면_캡처_2024-04-02_211207](/uploads/4f35b4ce46e63212c4a7c50c23ecd394/화면_캡처_2024-04-02_211207.png)
2. application.properties 에서 각자 설정한 mariaDB 포트번호랑 username, password 설정
```
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3307/eliceGym
spring.datasource.username=root
spring.datasource.password=0000
```
3. database 설정에 들어가서 schema 작성
- 혹시 intelliJ community version 을 쓰시는 분은 MySQL 워크벤치로 작업해주세요
![image](/uploads/02127f5ccb639b8cdc8b1abb0f0aa1db/image.png)
![image](/uploads/9713df55e77ab6f5fa1f75aff6208aa2/image.png)
![image](/uploads/d5159f17558f14508acf9ba67dab7701/image.png)
![image](/uploads/5615c073a523908328e4a7ea6d9b4c0c/image.png)



4. create.sql 밑 이하 sql 콘솔 실행

- database 파일에 들어가서 create.sql 실행                                            
![image](/uploads/a84e22141ec57d34025112cff0d181ab/image.png)
![image](/uploads/7c4d0883319df26d7ca74283b073e155/image.png)
- 전체 선택 한 후 왼쪽 위 run 클릭 (MySQL 워크벤치는 전체선택 후 ctrl+Enter, 아니면 run 버튼 누르기)
![image](/uploads/d0c076b4afd566a8179bc246ae523740/image.png)
- 그 뒤 items, users 도 똑같이 run 해준다


## 3. 현재 구현 상태
1. 메인 페이지에 card 로 상품 진열
    ![image](/uploads/54c6fc4e2bf9d29b9a210e1dfa542cfb/image.png)
<!-- 2. Header, Footer 구현 (로그인 상태에 따라 구성 요소 바뀜)
3. jwt 를 이용한 로그인, 로그아웃 구현
4. 장바구니에 담긴 상품 리스트 구현
5. 구매하기 누르면 장바구니안에 담긴 상품들을 구매
6. 주문 조회를 누르면 주문한 상품 볼 수 있음 -->

## 4. 구현 과제

- [ ]  회원가입 기능
- [ ]  회원정보 수정기능
- [ ]  주문 조회시 내가 주문한 물품만 조회
- [ ]  카테고리에 따라 상품 페이지 나누는 기능
- [ ]  상품리스트 → 상품 상세 페이지로 이동
- [ ]  상품추가 기능
- [ ]  상품을 장바구니에 담았을 때 장바구니에 담았다는 alert 뜨게 하기
- [ ]  상품 옵션, 구매 수량 추가하기
- [ ]  관리자와 일반 유저를 구별할 수 있는 roll 값 지정
- [ ]  상품 정렬 조작 기능 (높은 가격순, 낮은 가격순, 이름순)
