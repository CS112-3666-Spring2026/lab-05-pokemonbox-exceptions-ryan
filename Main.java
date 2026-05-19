import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int choice = -1;
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);

        Pokemon[] caught = {
                new Pokemon("Pikachu", "Electric"),
                new Pokemon("Bulbasaur", "Grass", "Poison"),
                new Pokemon("Charmeleon", "Fire"),
                new Pokemon("Squirtle", "Water"),
                new Pokemon("Butterfree", "Bug", "Flying"),
                new Pokemon("Pidgeotto", "Normal", "Flying")
        };

        System.out.println("Preloading Pokemon Box...");
        PokemonBox myBox = new PokemonBox(caught);
        System.out.println("...Done!\n");

        System.out.println("---------------------------");
        System.out.println("| Welcome to Pokemon Box! |");
        System.out.println("---------------------------\n");
        System.out.println(myBox);

        do {
            try {
                System.out.println("\nMAIN MENU\nWhat would you like to do?");
                System.out.println("\t1) Add a New Pokemon \n\t2) List All Pokemon \n\t3) Exit Program \n");
                System.out.print("Enter choice number> ");
                choice = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println();

                if (choice == 1) {
                    addPokemon(keyboard, myBox);
                } else if (choice == 2) {
                    System.out.println(myBox);
                } else if (choice == 3) {
                    tryAgain = false;
                } else {
                    System.out.println("Invalid choice, please pick a valid option from the menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer option.");
                keyboard.nextLine();
            }
        } while (tryAgain);

        keyboard.close();
        System.out.println("Thank you for using the Pokemon Box program :D see you later!");
    }

    public static void addPokemon(Scanner keyboard, PokemonBox myBox) {
        boolean added = false;

        while (!added) {
            try {
                System.out.println("Enter Pokemon Info to be added:");
                System.out.print("Enter Pokemon Name> ");
                String name = keyboard.nextLine();

                System.out.print("Enter Pokemon Type #1> ");
                String type1 = keyboard.nextLine();

                System.out.print("Enter Pokemon Type #2 (none if no second type)> ");
                String type2 = keyboard.nextLine();
                type2 = type2.equalsIgnoreCase("none") ? null : type2;

                Pokemon p = new Pokemon(name, type1, type2);
                myBox.add(p);

                System.out.println("\n" + p.getName() + " added!");
                added = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Pokemon data. Please enter a valid name and Pokemon type.");
            } catch (PokemonAlreadyExistsException e) {
                System.out.println(e.getMessage());
                System.out.println("Our region allows only one of each Pokemon type in the Box to support sustainability efforts.");
            }
        }
    }
}