package br.org.cesar.jedje.compiler.parser;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import br.org.cesar.jedje.compiler.JEdjeException;

public class JEdjeScanner {

	private InputStream stream;
	private char		current;
	
	public JEdjeScanner(InputStream _stream) throws IOException {
		this.stream  = _stream;
		this.current = this.next();
	}
	
	public JEdjeToken scan() throws IOException, JEdjeException {
		StringBuffer buffer = new StringBuffer();
		JEdjeToken 	 result = null;
		try {
			doPrologue();
			switch (this.current) {
				case '{': case '}':
				case ';': case ':':
				case ',':
					result = scanSpecialChar(this.current);
				break;
				case '/':
					removeComment();
					result = scan();
				break;
				default:
					if (isLetter(current)) {
						result = scanSpecialWord(buffer);
					} else
					if (Character.isDigit(current) 
							|| current == '+' || current == '-') {
						result = scanNumeric(buffer);
					} else
					if (current == '"') {
						result = scanString(buffer);
					}
				break;
			}
		} catch (EOFException e) {
			result = JEdjeToken.EOF;
		}
		
		if (result == null) {
			throw new JEdjeException("Unable to build token!");
		}
		
		return result;
	}
	
	private void removeComment() throws IOException, JEdjeException {
		this.current = this.next();
		if (this.current != '*') {
			throw new JEdjeException("Invalid comment.");
		}
		this.current = this.next();
		boolean foundStar = false;
		do {
			foundStar = this.current == '*';
			this.current = this.next();
		} while (!foundStar && this.current != '/');
		this.current = this.next();
	}

	private JEdjeToken scanString(StringBuffer buffer) throws IOException, JEdjeException {
		this.current = this.next();
		while (this.current != '"') {
			buffer.append(this.current);
			this.current = this.next();
		}
		
		if (this.current != '"') {
			throw new JEdjeException("String must end with '\"'.");
		}
		this.current = this.next();
		return new JEdjeToken(JEdjeToken.IDENTIFIER, buffer.toString());
	}

	private JEdjeToken scanSpecialChar(char current) throws IOException {
		JEdjeToken result = null;
		switch (current) {
			case '{':
				result = JEdjeToken.LEFT_KEY;
			break;
			case '}':
				result = JEdjeToken.RIGHT_KEY;
			break;
			case ';':
				result = JEdjeToken.SEMI_COLLON;
			break;
			case ':':
				result = JEdjeToken.COLLON;
			break;
			case ',':
				result = JEdjeToken.COMMA;
			break;
		}
		this.current = this.next();
		return result;
	}

	private JEdjeToken scanNumeric(StringBuffer buffer) throws IOException {
		int type = JEdjeToken.INTEGER;
		do {
			buffer.append(current);
			current = this.next();
		} while (Character.isDigit(current));
		if (current == '.') {
			type = JEdjeToken.FLOAT;
			do {
				buffer.append(current);
				current = this.next();
			} while (Character.isDigit(current));
		}
		return new JEdjeToken(type, buffer.toString());
	}

	private JEdjeToken scanSpecialWord(StringBuffer buffer) throws IOException {
		do {
			buffer.append(current);
			current = this.next();
		} while (!isWhiteSpace() && current != ',' && current != ';');
		return JEdjeToken.getToken(buffer.toString());
	}

	private boolean isWhiteSpace() {
		return String.valueOf(current).trim().length() == 0x00;
	}

	private boolean isLetter(char c) {
		boolean result = false;
		int 	code   = c;
		if (code >= 65 && code <= 90) {
			result = true;
		} else
		if (code >= 97 && code <= 122) {
			result = true;
		}
		return result;
	}

	private char next() throws IOException {
		int code = this.stream.read();
		if (code == -1) {
			throw new EOFException("End of file reached.");
		}
		return (char) code;
	}
	
	private void doPrologue() throws IOException {
		while (!isLetter(current) && !Character.isDigit(current)) {
			if (String.valueOf(current).trim().length() == 0x00) {				
				this.current = this.next();
			} else {
				break;
			}
		}
	}
}
