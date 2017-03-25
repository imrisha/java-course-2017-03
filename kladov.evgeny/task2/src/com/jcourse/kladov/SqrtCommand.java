package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Stack;

public class SqrtCommand implements Command {
	@Override
	public String getName() {
		return "SQRT";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		if (stack.empty())
			throw new RuntimeException("SQRT: Stack is empty");

		Double val = stack.pop();
		if (val < 0)
			throw new RuntimeException("SQRT: Negative argument");

		stack.push(Math.sqrt(val));
	}
}
