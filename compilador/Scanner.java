package compilador;

import java.util.Arrays;

public class Scanner {

    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
    }

    private char peek() {
        if (current < input.length)
            return (char) input[current];
        return '\0';
    }

    private void advance() {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    public Token nextToken() {
        skipWhitespace();
        char ch = peek();

        if (Character.isDigit(ch)) {
            return number();
        }

        if (Character.isLetter(ch)) {
            return identifier();
        }

        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '\0':
                return new Token(TokenType.EOF, "EOF");
            default:
                throw new Error("lexical error at " + ch);
        }
    }

    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }

        String n = new String(Arrays.copyOfRange(input, start, current));
        return new Token(TokenType.NUMBER, n);
    }

    private Token identifier() {
        int start = current;
        while (Character.isLetter(peek())) {
            advance();
        }

        String id = new String(Arrays.copyOfRange(input, start, current));
        return new Token(TokenType.IDENTIFIER, id);
    }

    private void skipWhitespace() {
        while (current < input.length && (peek() == ' ' || peek() == '\r' || peek() == '\t' || peek() == '\n')) {
            advance();
        }
    }
}