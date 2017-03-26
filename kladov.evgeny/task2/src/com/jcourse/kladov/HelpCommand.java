package com.jcourse.kladov;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.Stack;

public class HelpCommand implements Command {
	private PrintStream printStream;
	private Map<String, Command> commands;

	public HelpCommand(Map<String, Command> commands, PrintStream printStream) {
		this.commands = commands;
		this.printStream = printStream;
	}

	@Override
	public String getName() {
		return "HELP";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		printStream.println("List of available commands:");
		commands.forEach((k, v) -> printStream.println(k));
	}
}
