package by.builder;

public class Director {
    private RiddleBuilder riddleBuilder;

    public void setRiddleBuilder(RiddleBuilder pb) { riddleBuilder = pb; }
    public Riddle getCorrectAnswer() { return riddleBuilder.getCorrectAnswer(); }

    public void constructRiddle() {
        riddleBuilder.createRiddle();
        riddleBuilder.buildFirst();
        riddleBuilder.buildSecond();
        riddleBuilder.buildThird();
        riddleBuilder.buildCorrect();
    }
}
