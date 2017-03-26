package com.jcourse.kladov;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackCalc {

	private BufferedReader reader;
	private PrintStream printStream;
	private Map<String, Command> commands = new HashMap<>();
	private Map<String, Double> context = new HashMap<>();

	public StackCalc(BufferedReader reader, PrintStream printStream) {
		this.reader = reader;
		this.printStream = printStream;

		registerCommand(new DivCommand());
		registerCommand(new MulCommand());
		registerCommand(new AddCommand());
		registerCommand(new SubtractCommand());
		registerCommand(new SqrtCommand());
		registerCommand(new PopCommand());
		registerCommand(new DefineCommand(context));
		registerCommand(new PushCommand(context));
		registerCommand(new PrintCommand(printStream));
		registerCommand(new HelpCommand(commands, printStream));
	}

	public static void main(String[] args) {
		printUsage();

		try {
			BufferedReader reader;

			if (args.length > 0)
				reader = new BufferedReader(new FileReader(args[0]));
			else
				reader = new BufferedReader(new InputStreamReader(System.in));

			new StackCalc(reader, System.out).evaluate();
		} catch (FileNotFoundException e) {
			System.err.printf("FileNotFoundException: " + e.toString());
		}
	}

	private static void printUsage() {
		System.out.println("Stack calculator. Reads commands from provided file and print result.");
		System.out.println("Use HELP for list of available commands");
	}

	public void evaluate() {
		try {
			process();
		} catch (IOException e) {
			System.err.printf("IOException: " + e.toString());
		} catch (RuntimeException e) {
			System.err.println("Runtime exception: " + e.toString());
		}
	}

	private void process() throws IOException {
		Stack<Double> stack = new Stack<>();
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		tokenizer.commentChar('#');

		for (boolean eof = false; !eof; ) {
			tokenizer.wordChars('-', '-');
			Command cmd = null;

			switch (tokenizer.nextToken()) {
				case StreamTokenizer.TT_EOF:
					eof = true;
					break;

				case StreamTokenizer.TT_WORD:
					cmd = commands.get(tokenizer.sval);
					break;

				case '-':
					cmd = commands.get("-");
					break;

				case '+':
					cmd = commands.get("+");
					break;

				case '*':
					cmd = commands.get("*");
					break;

				case '/':
					cmd = commands.get("/");
					break;
			}
			if (cmd == null) {
				throw new RuntimeException("Unknown command: " + tokenizer.sval);
			}

			tokenizer.parseNumbers(); // reset to default interpret of -
			cmd.execute(stack, tokenizer);
		}
	}

	private void registerCommand(Command cmd) {
		commands.put(cmd.getName(), cmd);
	}
}
