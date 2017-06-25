
SRCDIR=src
BINDIR=bin
LIBDIR=lib

MAINFILE=Main

SOURCES=$(shell find $(SRCDIR) -name "*.java")
OBJECTS=$(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

JFLAGS=-d $(BINDIR) -sourcepath $(SRCDIR)

all: $(OBJECTS)

run: all
	@cd $(BINDIR)
	@java $(MAINFILE)

$(BINDIR):
	@mkdir $@

bin/%.class: src/%.java | $(BINDIR)
	javac $(JFLAGS) $<
	
clean:
	rm -rf $(BINDIR)/*
