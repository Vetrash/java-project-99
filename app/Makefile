setup:
	./gradlew build
build:
	./gradlew build

.PHONY: build
test:
	./gradlew test
run-dist:
	./gradlew installDist
	./build/install/app/bin/app
report:
	./gradlew test jacocoTestReport
checkstyle:
	./gradlew checkstyleMain checkstyleTest
run-chekstyleTest:
	./gradlew checkstyleTest

