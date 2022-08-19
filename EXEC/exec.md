# 빌드 및 배포 DOCS 

## 1. 사용 스택 버전 정보
* IntelliJ IDEA 2022.1.3
* Java JDK 8 
* Spring Boot 2.7.2
* MySQL 8.0.29
* JWT 0.9.1
---
* npm 8.11.0
* node 16.16.0
* Vue3
* OpenVidu 2.20.0
* 웹서버 : nginx

---
<br>

## 2. nginx 파일

```
server {
  listen 443 ssl;
  server_name i7a709.p.ssafy.io;
  
  ssl_certificate /etc/letsencrypt/live/i7a709.p.ssafy.io/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/i7a709.p.ssafy.io/privkey.pem;

  root /var/www/html/dist;
  
  index index.html;

  location / {
    try_files $uri $uri/ /index.html;
  }
}

server {
  listen 80;
  server_name i7a709.p.ssafy.io;
  root html;
  
  location / {
    return 301 https://i7a709.p.ssafy.io;
  }
} 
```
---
<br>

## 3. 배포 방법
### SERVER 
---
BE/6hatsBE 폴더로 들어간 뒤
다음 명령어 입력
```
git pull
chmod +x ./gradlew
./gradlew build
nohup sudo java -jar build/libs/6hats-0.0.1-SNAPSHOT.jar
ctrl + z
bg 1
```

### CLIENT
---
BE/6hatsFE 폴더로 들어간 뒤
다음 명령어 입력
```
git pull
npm i
npm run build
rm -r /var/www/html/dist
mv dist /var/www/html
```

### CLIENT
---

---
<br>

## 4. DB 접속 정보 및 프로퍼티 정보
```
server.port=8081
server.http.port=8888

server.ssl.key-store-type=PKCS12
server.ssl.key-store=classpath:keystore/key.p12
server.ssl.key-store-password=ahwkghldml779!

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://i7a709.p.ssafy.io:3306/6hats
spring.datasource.username=root
spring.datasource.password=ahwkfkwldksgdmsghldml779!

spring.jpa.database=mysql

spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

```