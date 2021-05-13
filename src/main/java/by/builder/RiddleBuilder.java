package by.builder;

public abstract class RiddleBuilder {
    protected Riddle riddle;

    protected String first, second, third, correct, picture;

    public Riddle getCorrectAnswer() { return riddle; }
    public void createRiddle() { riddle = new Riddle(); }

    public abstract void buildFirst();
    public abstract void buildSecond();
    public abstract void buildThird();

    public abstract void buildCorrect();
}
