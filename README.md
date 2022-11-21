# xehub task

# 목표
1. Spring Boot를 이용해 회원가입과 로그인이 가능한 웹 어플리케이션 구현
	* 프로젝트 생성, 초기 설정부터 직접 진행하는 onborading 과제.
	
## 요구사항

### DB 구축

아래 요구사항을 수용할 수 있는 데이터베이스 구축

DB는 로컬 환경에 구축하고 제출 시 Schema, Constraints 포함하여 export한다.(.sql 형태로 제출)

<br>

### 화면 구현

**회원가입 페이지**

- 아이디, 패스워드를 입력 받을 수 있도록 구현한다.

- `가입` 버튼을 클릭해 회원가입을 수행할 수 있도록 한다.

**로그인 페이지**

- 아이디, 패스워드를 입력 받을 수 있도록 구현한다.

- ‘로그인’ 버튼을 클릭해 로그인을 수행할 수 있도록 한다.

- 이미 로그인이 되어있는 경우 메인 페이지로 이동

**메인 페이지**

- 로그인이 되어있지 않은 경우 로그인 페이지로 이동

- 로그인한 유저의 아이디와 ‘마지막 로그인 시각은 `YYYY-MM-dd HH:mm:ss`입니다.’ 표시
    - 현재 로그인 시각이 아닌 직전의 로그인 시각을 표시

- 메인 페이지에서는 로그아웃을 수행할 수 있어야 한다.

- 클릭 이벤트 구현

    '타이머 보기' 버튼을 클릭하면 아래 내용을 담은 모달을 표시하도록 구현한다.
    - 쿠키 만료까지 남은 시간 표시(1초마다 자동 새로고침)
    - 표시형태 `mm:ss`



### 기능 구현

모든 요청은 `ajax`를 이용해 수행하도록 구현한다.

**회원가입**

- 아이디, 패스워드를 DB에 저장한다.

- 회원가입이 완료되면 로그인 페이지로 이동한다.

- 동일한 아이디로 중복 회원가입이 불가능하도록 구현

**로그인**

- 로그인 정보는 쿠키를 이용해서 처리한다.(쿠키명: LOGIN_USER) - Duration은 10분으로 설정

    쿠키에는 아래 정보를 포함하도록 한다.
    - 로그인 한 유저 아이디, 쿠키 만료 시각

- 로그인에 성공하면 메인 페이지로 이동한다.

- 로그인에 성공하면 DB에 유저아이디, 로그인 시각을 저장한다.


**로그아웃**

- 쿠키를 삭제한다.

- 로그아웃이 완료되면 로그인 페이지로 이동한다.

# 최종 결과물 
1. spring boot를 이용해 (JAVA) servlet으로 routing path 설정
2. routing된 페이지 html, javscript로 생성된 DOM node 객체 display
3. 로그인 시도 이후, controller에 등록된 model DAO CRUD process에 등록된 함수로 model DTO 생성
4. 생성된 DTO 객체의 오류 여부 검토 후, 에러 발생 여부에 따른 View 호출 분리.
5. 이상 없을 경우, 저장된 model DTO 객체를 DB에 저장 및 cookie에 user 정보 저장, (10분간 유지)
6. cookie에 user의 정보가 있을 경우, login page load와 함께 에러 검사 및 지장없을 경우, 자동로그인 구현
7. 로그인 시 접근 가능한 페이지 분리, 로그인 이후 button click시 현재 남은 쿠키 유지시간 mm:ss 단위로 display
8. 로그아웃 시, 저장된 cookie 정보 삭제, 재 로그인 시 새로운 cookie(expire time: 10 min)생성.

# 결과 확인 빌드 방법
# build process
  1. put sql data schemes		-> MYSQL 데이터 생성 및 유저 생성 userId: xehub, userPWD: 1234, database name: xehub_task
  2. open termial at clone file		-> git clone으로 코드 가져오기 (IntelliJ, JDK 11, gradle, yarn build) 
  3. cd src/java/main/webapp		-> node_module 설치를 위한 package.json 파일 위치로 이동.
  4. yarn install			-> package.json 파일이 있는 위치에서 yarn으로 필요한 package 정보 빌드
  5. cd ../../../ 			-> 프로젝트 root 폴더 위치로 이동.
  6. ./gradle bootRun			-> gradle로 번들링된 string boot 웹앱 실행( server info: appache tomcat, base port: 8080)
  7. open at localhost:8080		-> 브라우저에서 server가 실행된 주소로 이동해 실행되는 웹 확인.
  
  
# SQL 데이터베이스 정보. 

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/xehub_task
spring.datasource.username=xehub
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true


# MYSQL create data set query

``` MYSQL
CREATE TABLE User
(
    ID INT NOT NULL AUTO_INCREMENT,

    Name VARCHAR(30) NOT NULL,

    ReserveDate varchar(50) NULL,

    Password varchar(30) NOT NULL,

	CONSTRAINT PK_User PRIMARY KEY (ID)
);

INSERT INTO User (Name, ReserveDate, Password)
VALUES('신현민', '2016-02-16', 1234);

INSERT INTO User (Name, ReserveDate, Password)
VALUES('xehub', '2017-02-12', 4500);

```

