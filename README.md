# Prerequisites

- Install Spring Tool Suite (http://www.springsource.org/downloads/sts-ggts)
- Install Maven from the Eclipse Marketplace (Help menu)
- Install the JBehave plugin (https://github.com/Arnauld/jbehave-eclipse-plugin)

- Install eGit from the Eclipse Marketplace (Help menu), if you prefer to pull/push from within eclipse.

# How to run it

Run the project with the following run configurations:
`mvn clean integration-test -Dgroup=google`


## How to run it with Grid
1. start your hub

    java -jar selenium-server-standalone-2.40.0.jar -role hub

2. start your nodes (you need at least as many nodes as the threads specified in in the pom.xml)

    java -jar selenium-server-standalone-2.40.0.jar -role node -port 5588 -hub http://localhost:4444/grid/register -browser browserName=firefox,maxInstances=1,platform=LINUX
    java -jar selenium-server-standalone-2.40.0.jar -role node -port 5599 -hub http://localhost:4444/grid/register -browser browserName=firefox,maxInstances=1,platform=LINUX
    java -jar selenium-server-standalone-2.40.0.jar -role node -port 5500 -hub http://localhost:4444/grid/register -browser browserName=chrome,version=33,chrome_binary=/path/to/chrome/driver/binary,maxInstances=1,platform=LINUX

3. start the tests with Firefox

    mvn clean integration-test -Dgroup=google -DseleniumGrid=true

4. start the tests with a custom browser (dunno why it's not working with chrome)

    mvn clean integration-test -Dgroup=google -Dbrowser=chrome -DseleniumGrid=true


