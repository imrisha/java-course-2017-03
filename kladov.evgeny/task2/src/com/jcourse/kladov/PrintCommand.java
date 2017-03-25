package com.jcourse.kladov;

import java.io.StreamTokenizer;
import java.util.Stack;

public class PrintCommand implements Command {
	@Override
	public String getName() {
		return "PRINT";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) {
		if (stack.empty())
			throw new RuntimeException("PRINT: stack is empty");
		System.out.println(stack.peek());
	}
}
