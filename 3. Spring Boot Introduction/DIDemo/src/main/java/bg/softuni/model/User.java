package bg.softuni.model;

public record User(String firstName, String lastName, int age) {

    public String description() {
        return String.format("User %s %s with age: %d", firstName(), lastName(), age());
    }

}
