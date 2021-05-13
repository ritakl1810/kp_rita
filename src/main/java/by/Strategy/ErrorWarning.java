package by.Strategy;

public class ErrorWarning implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Проверьте почту!";
    }
}

