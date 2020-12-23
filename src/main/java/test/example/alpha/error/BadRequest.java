package test.example.alpha.error;

public class BadRequest extends RuntimeException {
    public BadRequest(String msg) {
        super("Bad request format: " + msg);
    }
}
