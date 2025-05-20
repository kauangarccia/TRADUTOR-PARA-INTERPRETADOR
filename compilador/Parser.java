package compilador;

public class Parser {

    private Scanner scan;
    private Token currentToken;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    public void parse() {
        expr();
        if (currentToken.type == TokenType.IDENTIFIER) {
            pop();
        }
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error: expected " + t + " but found " + currentToken.type);
        }
    }

    void expr() {
        number();
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            oper();
        }
    }

    void number() {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    void oper() {
        TokenType op = currentToken.type;
        match(op);
        number();

        if (op == TokenType.PLUS) {
            System.out.println("add");
        } else if (op == TokenType.MINUS) {
            System.out.println("sub");
        }
    }

    void pop() {
        System.out.println("pop " + currentToken.lexeme);
        match(TokenType.IDENTIFIER);
    }
}