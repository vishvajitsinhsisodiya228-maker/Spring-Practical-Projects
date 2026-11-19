GYMMANAGERSPRING - ANNOTATION CONFIGURATION + SPRING JDBC
=========================================================
Requirements: JDK 17+, Maven, MySQL Server.

1. Run sql/gym_db.sql in MySQL Workbench/phpMyAdmin.
2. Edit gym.config.AppConfig datasource URL, username and password.
3. Build:
       mvn clean compile
4. Verify the fresh table count (should be 0):
       mvn exec:java -Dexec.mainClass=gym.app.GymDbCheckApp
5. Run complete Spring/JDBC demo:
       mvn exec:java -Dexec.mainClass=gym.app.GymApp

Includes:
- @Component FeeCalculator, MembershipService, MemberRegistry, MemberDao
- @Configuration AppConfig with @Bean DecimalFormat, GymSettings, DataSource, JdbcTemplate
- Plan fee discounts: 3-5 months 5%, 6+ months 10%
- Invoice calculation: plan fee + registration fee + GST
- In-memory member registry and phone lookup
- JDBC save/findAll/findByPhone/changePlan/deleteById
- SQL uses ? placeholders; no value concatenation
- findByPhone/findById return null when no row exists
Note: GymApp demonstrates initial row count before inserts. Run GymDbCheckApp
first on a newly created table to confirm exactly 0.
