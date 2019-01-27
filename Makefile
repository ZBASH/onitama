.DEFAULT_GOAL := help

# -- context --
sources=$(shell find src/ -type f -name "*.java")

# -- run/stop --
## runs the cli
r:
	java -cp out/cli App
.PHONY: r

# -- build --
## builds the cli
b:
	javac $(sources) \
		-d out/cli \
		-cp ~/.m2/repository/org/jetbrains/annotations/16.0.2/annotations-16.0.2.jar
.PHONY: b

# -- help --
help:
	@awk "$$HELP" $(MAKEFILE_LIST)

define HELP
BEGIN {
	print "\033[4;37musage:\033[0m";
	print "  \033[1;37mmake <command>\033[0m\n";
	print "\033[4;37mcommands:\033[0m";
}
/^## (.*)$$/ {
	$$1=""; docs=$$0;
	getline;
	sub(/:/, "", $$1);
	printf "  \033[1;31m%-2s\033[0;90m %s\033[0m\n", $$1, docs;
}
endef
export HELP

.PHONY = help
