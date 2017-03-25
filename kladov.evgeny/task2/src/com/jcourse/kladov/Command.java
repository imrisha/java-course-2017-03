package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Stack;

public interface Command {
	String getName();

	void execute(Stack<Double> stack, StreamTokenizer st) throws IOException;
}
