
Use http://localhost:8082/SpringBoot_JPA_XML/h2-console to access H2 console in browser:
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:file:./Sandbox_Database;MODE=Oracle
User Name: sa
Password: sa

Set H2 to Oracle Mode:
spring.datasource.url=jdbc:h2:file:./Sandbox_Database;MODE=Oracle

Verify Mode:
select * from INFORMATION_SCHEMA.SETTINGS where NAME='MODE';

Or use DBeaver as an universal SQL client to access H2 database, but need to kill the exe process first.

Get the Running Port in Spring Boot:
https://www.baeldung.com/spring-boot-running-port

----------------------------------------------------------------------


