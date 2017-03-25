package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Stack;

public class SubtractCommand implements Command {
	@Override
	public String getName() {
		return "-";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		if (stack.empty())
			throw new RuntimeException("-: Stack is empty");
		else if (stack.size() == 1) {
			stack.push(-stack.pop());
		} else {
			Double a = stack.pop();
			Double b = stack.pop();
			stack.push(a - b);
		}
	}
}
