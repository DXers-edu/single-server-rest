# AWS를 이용한 시스템 설계 및 구축과 배포

## 🏁 단일 서버
**소스코드**: Spring boot (REST) 소스코드

---

## 📖 프로젝트 소개
이 저장소에는 Spring Boot를 활용한 REST API 애플리케이션 샘플 소스코드를 포함하고 있습니다.  
강의를 통해 AWS 환경에 시스템을 설계, 구축, 배포하는 과정을 단일 서버(하나의 머신)에서의 실행 방식을 체험할 수 있습니다.

---

## 🛠️ 사용 기술
- Java 21
- Spring Boot 3.4.6
- Spring Data JPA
- Spring Security
- MySQL
- Gradle

---

## 🚀 빌드 및 실행
1. 저장소 클론  
   ```bash
   git clone https://github.com/DXers-edu/single-server-rest.git
   cd single-server-rest
   ```

2. 프로젝트 빌드  
   ```bash
   ./gradlew clean build
   ```

3. 애플리케이션 실행  
   ```bash
   ./gradlew bootRun
   ```

---

## ⚙️ 설정 변경 (중요!)
`src/main/resources/application.properties` 파일

```properties
spring.application.name=single-server-rest

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/single_server?createDatabaseIfNotExist=true&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=prd_user
spring.datasource.password=StrongP!ssw0rd
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

jwt.secret=qwertyuioplkjhgfdsazxcvbnm1234567890
```
  
`/etc/systemd/system/single-server.service` 파일

```plaintext
[Unit]
Description=Single Server Application
After=network.target
[Service]
User=ubuntu
WorkingDirectory=/opt/single-server
ExecStart=/usr/bin/java -jar /opt/single-server/app.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10
StandardOutput=file:/opt/single-server/logs/app.log
StandardError=file:/opt/single-server/logs/app-error.log
[Install]
WantedBy=multi-user.target
```

---

## 📜 라이선스
MIT License  
