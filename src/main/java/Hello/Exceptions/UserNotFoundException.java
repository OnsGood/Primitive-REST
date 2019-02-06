package Hello.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super("could not find user - " + id);
    }
}
