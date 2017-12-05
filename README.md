# EmojicodeEditor
A text editor and IDE written in Java för Emojicode

Emojicode is an open-source, full-blown programming language consisting of emojis.
But it can be hard to read for new users and it can be difficult to know which
emojis to use.

The subject of this project is to create a text editor which fully understands
Emojicode and supports the software developer in creating software with Emojicode.

## Debugger
One important feature of IDE:s is to have a debugger. Therefore EmojicodeEditor
includes a compiler and debugger to let the user run the program step by step
and watch the status of the variables. The native compiler for Emojicode doesn't
support debugging of the program at this point, so EmojicodeEditor has its own
compiler and debugger.

### Emojicode packages with native binaries
EmojicodeEditor has full support of the Emojicode language, with one important exception. Emojicode packages can be accompanied by a native compiled binary. The internal compiler of EmojicodeEditor supports packages with native binaries, but the debugger cannot execute code in the native binary. The reason is that it could lead to unpredictable result if the debugger runs code in the native binary, for example if the code creates new threads or creates windows on the screen.

The standard packages that comes with Emojicode is emulated by EmojicodeEditor so code that uses the standard packages will work. And code that uses other packages that are written in Emojicode will work as long as EmojicodeEditor has access to the Emojicode source code of these packages.

## Download
The lastest development version [EmojicodeEditor.jar](https://danielb987.github.io/EmojicodeEditor/distribution/EmojicodeEditor.jar)
is generated on every new commit. There is no stable version yet.

## Contribution
Contributions are welcome. Please read the [contribution](https://github.com/danielb987/EmojicodeEditor/blob/master/CONTRIBUTING.md) page before contribution to the project.

### Javadoc
The documentation pages has [Javadoc for EmojicodeEditor](https://danielb987.github.io/EmojicodeEditor/javadoc/).

### Status checks
[![Build Status](https://travis-ci.org/danielb987/EmojicodeEditor.svg?branch=master)](https://travis-ci.org/danielb987/EmojicodeEditor) [![Coverage Status](https://coveralls.io/repos/github/danielb987/EmojicodeEditor/badge.svg?branch=master)](https://coveralls.io/github/danielb987/EmojicodeEditor?branch=master)

[Checkstyle](https://danielb987.github.io/EmojicodeEditor/checkstyle/checkstyle_errors.xml) -
[Checkstyle summary](https://danielb987.github.io/EmojicodeEditor/checkstyle/checkstyle_report.html)
