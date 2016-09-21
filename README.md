Sainsbury's web scraper
=======================

Console application that scrapes Sainsbury’s grocery site - Ripe Fruits page and stores in a file a JSON with the sum and all the products of the given page.

Requirements
------------
It requires Maven version >= 3 and JDK 1.8

Running the application
-----------------------
Clone the repository and use Maven to compile and run it:

git clone <REPO_URL>
cd sainsbury-exercise-master

1. mvn clean verify site --> Should run unit and integration tests and generates reporting documentation
2. mvn exec:java  --> Should generate a results.txt at the root's folder
3. mvn package --> Should generate a JAR with dependencies (target/webscraper-1.0-SNAPSHOT-jar-with-dependencies.jar)
    3.1. java -jar target/webscraper-1.0-SNAPSHOT-jar-with-dependencies.jar

Documentation
-------------

After above first step, you can find at target/site/index.html the Javadoc documentation and failsafe, surefire reports




