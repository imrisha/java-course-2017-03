package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Stack;

public class PopCommand implements Command {
	@Override
	public String getName() {
		return "POP";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		if (stack.empty())
			throw new RuntimeException("POP: Stack is empty");
		stack.pop();
	}
}
