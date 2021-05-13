package by.Strategy;

public class ErrorName implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Имя не должно содержать специальные символы и цифры";
    }
}
