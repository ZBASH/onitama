SOURCES=$(shell find src/ -type f -name "*.java")

build:
	javac $(SOURCES) \
		-d out/cli \
		-cp ~/.m2/repository/org/jetbrains/annotations/16.0.2/annotations-16.0.2.jar

run:
	java -cp out/cli App

.PHONY: build run

