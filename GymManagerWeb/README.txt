GYMMANAGERWEB - SPRING MVC DYNAMIC WEB PROJECT
================================================
Target: Apache Tomcat 10.1+ (Jakarta Servlet 6), Java 17+, MySQL.
Spring Framework 6.1.14. This is a WAR project, not Spring Boot.

DATABASE
1. Run sql/gym_db.sql in MySQL.
2. Edit gym.config.AppConfig username/password and JDBC URL.

BUILD
    mvn clean package
Deploy target/GymManagerWeb.war to Tomcat 10.1+.
Or import this Maven WAR project into Eclipse as an Existing Maven Project,
then configure Tomcat 10.1 runtime and add the project to the server.

URLS
    http://localhost:8080/GymManagerWeb/home
    http://localhost:8080/GymManagerWeb/addMember
    http://localhost:8080/GymManagerWeb/members

FEATURES
- HomeController with ModelMap gymName.
- Add Member GET/POST, fee calculation, validation and duplicate phone check.
- Members table with Edit link and POST Delete form.
- Edit GET pre-fills plan/months; update POST recalculates fee and redirects.
- Delete redirects with fixed msg=deleted or msg=notfound messages.
- Search by phone handles found, missing and empty phone.
- JSP pages use scriptlets; no JSTL.

VALIDATION
- Name cannot be blank (trimmed).
- Phone must match exactly 10 digits.
- Months must be between 1 and 12.
- Duplicate phone numbers are rejected.
- Platinum for 6 months is ₹9,720 (₹1,800 x 6 less 10%).
- Uses MemberDao.findByPhone; no extra SQL was added for search.

IMPORTANT
This uses Spring 6 / Jakarta APIs, so use Tomcat 10.1 or newer.
Tomcat 9 uses javax.servlet and is not compatible with this Spring 6 WAR.
