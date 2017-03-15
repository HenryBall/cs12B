# Henry Ball
# hfball
# 12M
# 05-30-16
# Makefile
# Makefile for Dictionary.java

# Variables
JARFILE    = DictionaryClient
MAINCLASS  = DictionaryClient
SOURCES    = Dictionary.java DictionaryInterface.java DictionaryClient.java\
             KeyNotFoundException.java DuplicateKeyException.java
CLASSES    = Dictionary.class DictionaryInterface.class DictionaryClient.class Dictionary\$$Node.class\
             KeyNotFoundException.class DuplicateKeyException.class

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)
