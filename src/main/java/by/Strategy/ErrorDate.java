package by.Strategy;

public class ErrorDate implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Дата отправления не может быть больше даты отъезда";
    }
}
