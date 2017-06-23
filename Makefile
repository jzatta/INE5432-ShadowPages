
SRCDIR=src
BINDIR=bin
LIBDIR=lib

MAINFILE=java.class

SOURCES=$(shell find $(SRCDIR) -name "*.java")
OBJECTS=$(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

JFLAGS=-d $(BINDIR) -sourcepath $(SRCDIR)

all: $(OBJECTS)

run: all
	java $(BINDIR)/$(MAINFILE)

$(BINDIR):
	@mkdir $@

bin/%.class: src/%.java | $(BINDIR)
	javac $(JFLAGS) $<
	
clean:
	rm -rf $(BINDIR)/*
