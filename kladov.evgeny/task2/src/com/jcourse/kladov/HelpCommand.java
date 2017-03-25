package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.Stack;

public class HelpCommand implements Command {
	private Map<String, Command> commands;

	public HelpCommand(Map<String, Command> commands) {
		this.commands = commands;
	}

	@Override
	public String getName() {
		return "HELP";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		System.out.println("List of available commands:");
		commands.forEach((k, v) -> System.out.println(k));
	}
}
