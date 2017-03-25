package com.jcourse.kladov;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.Stack;

public class DefineCommand implements Command {

	private Map<String, Double> context;

	public DefineCommand(Map<String, Double> context) {
		this.context = context;
	}

	@Override
	public String getName() {
		return "DEFINE";
	}

	@Override
	public void execute(Stack<Double> stack, StreamTokenizer st) throws IOException {
		int token = st.nextToken();

		if (token != StreamTokenizer.TT_WORD)
			throw new RuntimeException("DEFINE: Identifier expected");

		String varName = st.sval;
		token = st.nextToken();

		if (token != StreamTokenizer.TT_NUMBER)
			throw new RuntimeException("DEFINE: Number expected");

		context.put(varName, st.nval);
	}
}
