# Contribution

## Code checking
The EmojicodeEditor project uses Travis CI to run some tests and code validation
to ensure the quality of the code. These tests are:
- JUnit tests: Run the tests in the test packages.
- Jacoco: Checks how much of the code that is covered by tests. The result is
published on Coverall.
- Checkstyle: Check the coding style.

## Coding style
EmojicodeEditor uses [checkstyle](http://checkstyle.sourceforge.net/) to enforce
[Sun coding style](http://checkstyle.sourceforge.net/sun_style.html).

# Exceptions
Sun's coding standard requires no spaces at the end of the line. This project allows
lines with only spaces.

Max line length is 100 characters for this project.

# NetBeans generated files
Some files which are in part generated by NetBeans cannot follow the Sun coding style.
NetBeans doesn't allow changes in some parts of these files. In order to handle that,
some exceptions must be made to checkstyle.

For classes that extends JFrame and that are controlled by NetBeans, the following rules
apply. See the emojicodeeditor.MainWindow class for an example.

Add these lines at the top of the class:
```
//CHECKSTYLE.OFF: FinalParametersCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
//CHECKSTYLE.OFF: WhitespaceAroundCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
//CHECKSTYLE.OFF: LineLengthCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
```
Add these lines at the bottom of the class:
```
//CHECKSTYLE.ON: LineLengthCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
//CHECKSTYLE.ON: WhitespaceAroundCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
//CHECKSTYLE.ON: FinalParametersCheck - Several methods in this class is created by the
// NetBeans IDE and we cannot change them.
```
Add this line before the method initComponents()
```
//CHECKSTYLE.OFF: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
```
Add this line after the method initComponents()
```
//CHECKSTYLE.ON: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
```