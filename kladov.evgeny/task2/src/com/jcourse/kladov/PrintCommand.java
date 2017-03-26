package com.jcourse.kladov;

import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.Stack;

public class PrintCommand implements Command {
	private PrintStream printStream;

	@Override
	public String getName() {
		return "PRINT";
	}

	PrintCommand(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) {
		if (stack.empty())
			throw new RuntimeException("PRINT: stack is empty");
		printStream.println(stack.peek());
	}
}
