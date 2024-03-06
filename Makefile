JUnit.jar  = junit-platform-console-standalone-1.8.1.jar

all: compile jarleague jartourn jarmaster runMaster

doc: compile
	javadoc -sourcepath src -subpackages SportsCompet -d doc

compile:
	javac -classpath target/classes -sourcepath src src/SportsCompet/*.java src/SportsCompet/*/*.java -d target/classes

compile-test: compile
	javac -cp $(JUnit.jar) -sourcepath src:tests tests/SportsCompet/*.java tests/SportsCompet/*/*.java -d target/tests-classes/

tests: compile-test
	java -jar $(JUnit.jar) \
           --class-path target/classes:target/tests-classes \
           --scan-class-path

jarleague: compile
	jar cvfm jars/league.jar manifest-league -C target/classes .

jartourn: compile
	jar cvfm jars/tournament.jar manifest-tournament -C target/classes .

jarmaster: compile
	jar cvfm jars/master.jar manifest-master -C target/classes .

runLeague:
	java -jar jars/league.jar

runTournament:
	java -jar jars/tournament.jar

runMaster:
	java -jar jars/master.jar

clean:
	-rm -rf target jars/*.jar doc
