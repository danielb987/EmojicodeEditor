# Project goal
The goal of this project is:
- Create an easy to use text editor to write Emojicode programs.
- Have a toolbar with all the important emojis, grouped by function.
- Make it very easy to compile and run the Emojicode program using
the native Emojicode compiler.
- Include a compiler and debugger that allows the editor to show the
developer exactly how the Emojicode program works and what it does.

## Create an easy to use text editor to write Emojicode programs.
In order to understand an Emojicode program, the developer need to
know several emojis. If the developer doesn't, the program looks
like a pile of strange symbols.

It's therefore useful if the developer could point at one symbol and
that the editor could tell the user what that symbol means and what
it does.

## Have a toolbar with all the important emojis, grouped by function.
Most keyboards doesn't have emojis on the keyboard, which makes it
difficult to write Emojicode programs. There are tools that can
assist by showing the emojis, but these tools are not focused on
Emojicode and therefore doesn't group the emojis in a way that makes
sence for an Emojicode developer.

## Make it very easy to compile and run the Emojicode program using
the native Emojicode compiler.
This project is NOT about replacing the native Emojicode compiler.
The goal is to help more people develop Emojicode programs and use
the native compiler.

Therefore, the editor supports compiling the Emojicode program by
selecting a menu choice or by pressing a function key, as well as
placing the cursor on the spot there compiler errors occur.

It should also be possible to run a compiled Emojicode program by
selecting a menu choice or by pressing a function key. And for
text mode programs, the output of the program should be shown in
a "output" window inside the editor so that the user should not
need to run the program in a terminal window.

In case the program needs both input and output, maybe a "screen"
window should be created during the execution of the Emojicode
program.

## Include a compiler and debugger
One very good way to learn a language is to run a program in the
a debugger step by step to see exactly what every step does in the
program. The native emojicode compiler and runtime environment
doesn't support a debugger at this time. So instead, a debugger
is developed for the editor.
