include ./Makefile.base.mk

# -- cosmetics --
help-column-width = 12

# -- context --
tools-swift    = swift
tools-swiftfmt = swift-format --configuration .swift-format.json --recursive ./

# -- init --
## initializes dev environment
init: init/pre init/base
.PHONY: init

# -- init/helpers
init/base:
	brew bundle -v
.PHONY: init/base

init/pre:
ifeq ("$(shell command -v brew)", "")
	$(info ✘ brew is not installed, please see:)
	$(info - https://brew.sh)
	$(error 1)
endif
.PHONY: init/pre

# -- run --
## runs the cli
run:
	$(tools-swift) run
.PHONY: run

## cleans and runs the cli
r/clean: b/clean run
.PHONY: r/clean

## runs the cli in the debugger
r/debug: build
	lldb ./.build/debug/OnitamaCLI	
.PHONY: r/debug

# -- build --
## builds the cli
build:
	$(tools-swift) build
.PHONY: build

## cleans the cli
b/clean:
	$(tools-swift) package clean
.PHONY: clean

# -- verify --
## runs all verification steps
verify: v/format v/test
.PHONY: verify

## checks the code formatting
v/format:
	$(tools-swiftfmt) --mode lint
.PHONY: v/format

## auto-fixes the code formatting
v/format/fix:
	$(tools-swiftfmt) --in-place
.PHONY: v/format

## runs the tests
v/test:
	$(tools-swift) test
.PHONY: v/test
