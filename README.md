# xehub task

# build process
  1. put sql data schemes
  2. open termial at clone file
  3. ./gradlew bootRun
  4. open at localhost:8080
  
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/xehub_task
spring.datasource.username=xehub
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true


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

