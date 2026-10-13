setup:
	./gradlew build install
build:
	./gradlew build
test:
	./gradlew test
run-dist:
	./gradlew installDist
	./build/install/app/bin/app
report:
	./gradlew test jacocoTestReport
checkstyle:
	./gradlew checkstyleMain checkstyleTest

.PHONY: run-chekstyleTest
run-chekstyleTest:
	./gradlew checkstyleTest

.PHONY: build test run-dist report checkstyle
