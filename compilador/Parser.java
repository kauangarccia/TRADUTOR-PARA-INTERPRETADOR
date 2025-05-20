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
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error: expected " + t + " but found " + currentToken.type);
        }
    }

    void expr() {
        number(); // Processa o primeiro número
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            oper(); // Processa operador seguido de número
        }
    }

    void number() {
        System.out.println("<" + currentToken.type.name() + ">" + currentToken.lexeme + "</" + currentToken.type.name() + ">");
        match(TokenType.NUMBER);
    }

    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            System.out.println("<PLUS>+</PLUS>");
            match(TokenType.PLUS);
            number();
        } else if (currentToken.type == TokenType.MINUS) {
            System.out.println("<MINUS>-</MINUS>");
            match(TokenType.MINUS);
            number();
        }
    }
}