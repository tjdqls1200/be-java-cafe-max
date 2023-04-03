## 학습 계획

---

<br>

[ ] 게시글 기능 구현 (이전 미션 2단계)
[ ] Spring JDBC 사용, Repository 구현체 교체
[ ] AWS 학습
[ ] AWS 배포


<br>

# 게시글

---

<br>

**Article**

- 제목
    - 2글자 이상 30글자 이하
- 본문
    - 0글자 이상 1000글자 이하

<br>

**URL**

---

게시글 리스트 조회
- [GET] "/articles"
  게시글 단건 조회
- [GET] "/articles/{id}"
  게시글 작성폼
- [GET] "/articles/write"
  게시글 작성
- [POST] "/articles"

<br>

**구현 목록**

[ ] Service  
- 게시글 저장, 조회, 리스트 조회  
- 예외 처리  
- 테스트 코드  

[ ] Repository  
- 게시글 저장, 조회, 리스트 조회  
- 테스트 코드  

[ ] Controller  
- 게시글 조회, 작성 매핑  
- dto 검증  
- 테스트 코드  

[ ] Thymeleaf  

<br>

## 학습 리스트 및 생각

---

<br>

**학습 리스트**


<br>

**생각**

- 이번주부터는 미션 구현을 빠르게 끝내고 리펙토링을 하면서 원하는 부분을 더 학습 해야겠다.  