package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Todo;

import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void app(UserFlow userFlow){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the todo application");
        System.out.println("Select an option to continue e.g 1");
        System.out.println("Or enter 99 to quit");
        System.out.println("1. Register\n2. Login\n3. Add a todo\n4. Get all todos\n5. Get all active todos\n6. Get all completed todos\n7. Delete a todo\n8. Update a todo \n9. Search for a todo\n10. Update password");

        Integer selectedOption = in.nextInt();

        if(selectedOption == 99){
            System.exit(99);
        }

        while(selectedOption > 10 || selectedOption < 1){
            System.out.println("Select a valid option to continue e.g 1");
            System.out.println("Or enter 99 to exit");
            selectedOption = in.nextInt();
            if(selectedOption == 99){
                System.exit(1);
            }
        }

        switch (selectedOption) {
            case 1 -> userFlow.registerUser();
            case 2 -> userFlow.loginUser();
            case 3 -> userFlow.createTodo();
            case 4 -> userFlow.getAllTodos();
            case 5 -> userFlow.getAllActiveTodos();
            case 6 -> userFlow.getAllCompletedTodos();
            case 7 -> userFlow.deleteTodo();
            case 8 -> userFlow.updateteTodo();
            case 9 -> userFlow.search();
            case 10 -> userFlow.updatePassword();
            default -> System.out.println("Invalid number");
        }

        System.out.println("\nWould you like to perform another operation?\n1. Yes\n2. No");
        Integer restart = in.nextInt();

        while(restart != 1 && restart != 2){
            System.out.println("Select a valid option to continue e.g 1");
            restart = in.nextInt();
        }
        if(restart == 1){
            app(userFlow);
        }else{
            in.close();
        }
    }
    public static void main(String[] args){
        UserFlow userFlow = new UserFlow();

        app(userFlow);
    }
}
