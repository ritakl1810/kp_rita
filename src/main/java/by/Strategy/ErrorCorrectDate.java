package by.Strategy;

import by.validators.Date;

public class ErrorCorrectDate implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Дата не может быть меньше " + Date.dateNow();
    }
}
