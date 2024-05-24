build:
	./app/gradlew clean build

report:
	./app/gradlew jacocoTestReport

start:
	./app/gradlew bootRun --args='--spring.profiles.active=development'

start-prod:
	./app/gradlew bootRun --args='--spring.profiles.active=production'

install-dist:
	./app/gradlew clean installDist

start-dist:
	./app/build/install/app/bin/app

.PHONY: build
