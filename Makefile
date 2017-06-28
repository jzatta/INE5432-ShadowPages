
SRCDIR=src
BINDIR=bin
LIBDIR=lib

MAINFILE=Main$$1
PROJNAME=ShadowPages

SOURCES=$(shell find $(SRCDIR) -name "*.java")
OBJECTS=$(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES)) 

JFLAGS=-d $(BINDIR) -sourcepath $(SRCDIR)

all: $(PROJNAME).jar

run: all
	@java -jar $(PROJNAME).jar

$(BINDIR):
	@mkdir $@

bin/%.class: src/%.java | $(BINDIR)
	javac $(JFLAGS) $<

$(PROJNAME).jar: $(OBJECTS)
	@echo "Manifest-Version: 1.0" > manifest.txt
	@echo "Class-Path: ." >> manifest.txt
	@echo "Main-Class: Main" >> manifest.txt
	@echo "" >> manifest.txt
	jar -cfe $@ Main $(subst bin/,-C bin ,$^) -C bin Main\$$1.class
	
clean:
	rm -rf $(BINDIR)/*
	rm $(PROJNAME).jar
