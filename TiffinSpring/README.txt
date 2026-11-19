TIFFINSPRING - CONSOLE / SPRING CORE PRACTICALS
================================================
Java 17+ and Maven required. This is a plain console project, not Spring Boot.

Build:
    mvn clean compile

Task 1/3 - Manual Java dependency injection:
    mvn exec:java -Dexec.mainClass=tiffin.TiffinBillApp

Spring JAR/module check:
    mvn exec:java -Dexec.mainClass=tiffin.SpringModuleCheck
Maven downloads Spring Core, Beans, Context, JDBC and AOP dependencies.

XML constructor/setter configuration and delivery policy:
    mvn exec:java -Dexec.mainClass=tiffin.TiffinSpringApp
Festival no-tax/free-delivery XML:
    mvn exec:java -Dexec.mainClass=tiffin.TiffinSpringApp -Dexec.args="beans-festival.xml"

Plan list:
    mvn exec:java -Dexec.mainClass=tiffin.PlanListApp

Prototype/singleton scope:
    mvn exec:java -Dexec.mainClass=tiffin.ScopeDemoApp
Expected ticket numbers 1001, 1002, 1003 and singleton comparison true.

Two-day trial packs:
    mvn exec:java -Dexec.mainClass=tiffin.TrialPackBill

The final OrderService is setter-injected and configured byType in XML.
The delivery values 30 and 300 are kept in XML, not Java source.
