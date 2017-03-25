package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.Stack;

public class PushCommand implements Command {

	private Map<String, Double> context;

	PushCommand(Map<String, Double> context) {
		this.context = context;
	}

	@Override
	public String getName() {
		return "PUSH";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		int token = st.nextToken();

		if (token == StreamTokenizer.TT_WORD) {
			Double val = context.get(st.sval);
			if (val == null)
				throw new RuntimeException("PUSH: Variable not found " + st.sval);
			stack.push(val);
		} else if (token == StreamTokenizer.TT_NUMBER) {
			stack.push(st.nval);
		} else {
			throw new RuntimeException("PUSH: Number expected");
		}
	}
}
