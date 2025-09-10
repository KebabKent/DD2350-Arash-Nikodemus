SRC_DIR = src
OUT_DIR = out
MAIN_CLASS = lab1.Main

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all run clean

all:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

run: all
	@echo "Running the program...\n\n\n"
	java -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(OUT_DIR)
