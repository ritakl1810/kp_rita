package by.Strategy;

public class ErrorPassword  implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Пароль должен быть длиннее 6 символов";
    }
}
