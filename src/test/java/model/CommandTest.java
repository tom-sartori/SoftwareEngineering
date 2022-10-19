package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void test() {
        String commandString = "login";
        String argument = "Jean";
        String message = "#" + commandString + " " + argument;


        Command command = new Command(message);

        assertEquals(message, command.getMessage());
        assertEquals(commandString, command.getCommand());
        assertEquals(argument, command.getArgumentList().get(0));
    }

}