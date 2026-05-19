public class PokemonAlreadyExistsException extends Exception {
    public PokemonAlreadyExistsException() {
        super("Pokemon already exists in this box.");
    }

    public PokemonAlreadyExistsException(String message) {
        super(message);
    }
}