## Cookie

Stateless Connection(서버가 클라이언트의 상태 정보를 저장하지 않는 연결)에서
쿠키가 활용된다.  
`Cookie` : 서버가 클라이언트 쪽에 저장하는 정보  

**먼저 쿠키를 읽는다.**    

```java
	Cookie[] cookies = request.getCookies();
```
현재 request에 있는 모든 쿠키를 불러온다.

```java
	if(cookies != null && cookies.length>0){
		String cookieName = cookie.getName();
		if("visitCount".equals(cookieName)){
			visitCount = Integer.parseInt(cookie.getValue());
		}
	}
```
쿠키의 이름에 사용자가 설정한 visitCount라는 이름의 쿠키가 있으면
그 값을 저장  
_쿠키의 값_은 String으로 들어옴  

**그 다음 원하는 값으로 설정 후 쿠키를 쓴다.(굽는다)**

```java
	visitCount++;
	Cookie cookie = new Cookie("visitCount",String.valueOf(visitCount));
```
쿠키의 값을 수정하는게 아니라 쿠키 이름 설정후 값을 세팅하여 새로운 쿠키를 생성  

그 다음 쿠키 생존 기간과 경로 설정(어디서 쿠키가 생성되었는지)  

```java
	cookie.setMaxAge(24*60*60) //1day
	cookie.setPath(request.getContextPath());
```
경로 설정 할 때 request.getContextPath()가 굉장히 많이 나오는데 
context란 문맥으로 운영체제의 context는 프로세스 서버측에서의 context는 지금 구동되고 있는 어플리케이션이다. 즉, /mysite02/  

모든 세팅을 한후에 response 객체에 추가해준다.  

```java
	response.addCookie(cookie);
```

## session을 이용한 인증처리

사용자가 request를 보낼 때 SessionFilter를 거치게된다.(여기서 Jsessionid를 설정하게된다)  
서버 내에는 Jessionid와 HttpSession이 매핑된 테이블이 내장되어있음(SessionManager)  

해당 request의 session을 가져온다.  

```java
	HttpSession session = request.getSession(true) //true로 설정하면 HttpsSession객체로 가져옴
```
그리고 해당 세션에 user 정보를 넣어준다.  

```java
	session.setAttribute("authUser", userVo);
```

세션에 있는 사용자 정보를 가져온다.(jsp에서)

```java
	UserVo authUser = (userVo)session.getAttribute("authUser");
```
jsp에는 HttpSession session객체가 이미 생성되어있음
***

-jsp는 data를 받아 화면에 뿌려준다.(렌더링)
-RequestDispach : 요청별로 view(jsp)를 분리
-foword : view로 요청을 연장시키는것 (client->conroller->view)
-절대경로 : / 로 시작한다, 상대경로 비추
-get은 데이터가 header에 post는 데이터가 body에
-post방식은 encoding을 해줘야한다.
-project이름을 변경할 때는 search->file search 로 기존 플젝이름을 검색후 모조리 변경 
-에자일 : 짧게 설계~테스트(마일스스톤, 이터레이션)를 반복하여 완성하는 방식
-사용자 스토리를 잘짜야한다.

