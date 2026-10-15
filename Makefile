setup:
	cd app && ./gradlew build
build:
	cd app && ./gradlew build
test:
	cd app &&  ./gradlew test
run-dist:
	cd app && ./gradlew installDist
	cd app && ./build/install/app/bin/app
report:
	cd app && ./gradlew test jacocoTestReport
checkstyle:
	cd app && ./gradlew checkstyleMain checkstyleTest
run-chekstyleTest:
	cd app && ./gradlew checkstyleTest

