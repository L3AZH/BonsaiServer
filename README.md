# BonsaiServer
Config file 
```bash
#Server port configuration
server.port=10093
server.tomcat.max-http-post-size=-1
spring.servlet.multipart.max-file-size=30MB
spring.servlet.multipart.max-request-size=100MB

#Basic Auth configuration
basic.auth.name=yourname
basic.auth.password=yourpass

#Database configuration
spring.datasource.drive-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bonsaidb
spring.datasource.username=your username db
spring.datasource.password=your pass

#JPA / HIBERNATE configuration
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#auto add entity when create table
spring.jpa.hibernate.ddl-auto=update

#JWT configuration
jwt.credential=asfsagfsagfsagfsa156asdsadsaf
jwt.expiration=86400000

#Email config
spring.mail.default-encoding=UTF-8
spring.mail.host=smtp.gmail.com
spring.mail.username=your@gmail.com
spring.mail.password=yourpass
spring.mail.port=587
spring.mail.protocol=smtp
spring.mail.test-connection=false
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


```
