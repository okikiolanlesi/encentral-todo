package org.example;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.map.HashedMap;
import org.example.entities.Todo;
import org.example.entities.User;
import org.example.services.TodoService;
import org.example.services.UserService;

import java.util.Scanner;

public class UserFlow {
    HashedMap<String, User> users = new HashedMap<String, User>();
    Bag<Todo> todos = new HashBag<Todo>();
    UserService userService;
    TodoService todoService;
    User loggedInUser;
    public UserFlow(){
        this.userService = new UserService(users);
        this.todoService = new TodoService(todos);
        userService.register("okiki", "okiki");
    }

    public void registerUser(){
        System.out.println("Please provide a email: ");
        Scanner in = new Scanner(System.in);
        String email = in.nextLine();
        System.out.println("Please provide a password: ");
        String password = in.nextLine();
        String response = userService.register(email, password);
        System.out.println(response);
    }
    public void loginUser(){
        System.out.println("Please provide your email: ");
        Scanner in = new Scanner(System.in);
        String email = in.nextLine();
        System.out.println("Please provide your password: ");
        String password = in.nextLine();
        String response = userService.login(email, password);

        if(response.equals("User logged in successfully successfully")){
            loggedInUser = users.get(email);
        }
        System.out.println(response);
    }

    public void updatePassword(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        Scanner in = new Scanner(System.in);

        System.out.println("Please provide your new password: ");
        String newPassword = in.nextLine();
        String response = userService.updatePassword(loggedInUser.getEmail(), newPassword);

        System.out.println(response);
    }

    public void createTodo(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Please provide a name for your todo: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Please provide details for your todo: ");
        String details = in.nextLine();

        String response = todoService.create(name, details, loggedInUser.getEmail());

        System.out.println(response);
    }

    public void getAllTodos(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Here are all the todos: ");
        Bag<Todo> allTodos = todoService.getAll();
        for(Todo todo: allTodos){
            System.out.println(todo);
        }
    }

    public void getAllActiveTodos(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Here are all the active todos: ");
        Bag<Todo> allActiveTodos = todoService.getAllActive();
        for(Todo todo: allActiveTodos){
            System.out.println(todo);
        }
    }

    public void getAllCompletedTodos(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Here are all the completed todos: ");
        Bag<Todo> allCompletedTodos = todoService.getAllCompleted();
        for(Todo todo: allCompletedTodos){
            System.out.println(todo);
        }
    }

    public void deleteTodo(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Please provide the identifier for the todo to be deleted: ");
        Scanner in = new Scanner(System.in);
        String identifier = in.nextLine();


        String response = todoService.delete(identifier);

        System.out.println(response);
    }

    public void updateteTodo(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        System.out.println("Please provide the identifier for the todo to be updated: ");
        Scanner in = new Scanner(System.in);
        String identifier = in.nextLine();
        System.out.println("What would you like to update: ");
        System.out.println("Select an option to continue e.g 1");
        System.out.println("Or enter 99 to quit");
        System.out.println("1. Name\n2. Details\n3. Status");

        Integer selectedOption = in.nextInt();

        if(selectedOption == 99){
            return;
        }

        while(selectedOption > 3 || selectedOption < 1){
            System.out.println("Select a valid option to continue e.g 1");
            System.out.println("Or enter 99 to exit");
            selectedOption = in.nextInt();
            if(selectedOption == 99){
                return;
            }
        }

        switch (selectedOption) {
            case 1 -> updateName(identifier);
            case 2 -> updateDetails(identifier);
            case 3 -> updateStatus(identifier);
            default -> System.out.println("Invalid number");
        }

    }
    private void updateName(String identifier){
        System.out.println("Please provide the new name for the todo to be updated: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        String response = todoService.updateName(identifier, name);
        System.out.println(response);
    }

    private void updateDetails(String identifier){
        System.out.println("Please provide the new details for the todo to be updated: ");
        Scanner in = new Scanner(System.in);
        String details = in.nextLine();

        String response = todoService.updateDetails(identifier, details);
        System.out.println(response);
    }

    private void updateStatus(String identifier){
        System.out.println("What would you like to change the status to: ");
        System.out.println("Select an option to continue e.g 1");
        System.out.println("Or enter 99 to quit");
        System.out.println("1. active\n2. completed");
        Scanner in = new Scanner(System.in);

        Integer selectedOption = in.nextInt();

        if(selectedOption == 99){
            return;
        }

        while(selectedOption > 2 || selectedOption < 1){
            System.out.println("Select a valid option to continue e.g 1");
            System.out.println("Or enter 99 to exit");

            selectedOption = in.nextInt();
            if(selectedOption == 99){
                return;
            }
        }
        String response = "";
        switch (selectedOption) {
            case 1 -> response = todoService.updateStatus(identifier, "active");
            case 2 -> response = todoService.updateStatus(identifier, "completed");
            default -> System.out.println("Invalid number");
        }

        System.out.println(response);
    }

    public void search(){
        Boolean loggedIn = checkIfLoggedIn();
        if(!loggedIn){
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("How would you like to search: ");
        System.out.println("Select an option to continue e.g 1");
        System.out.println("Or enter 99 to quit");
        System.out.println("1. By name\n2. By details\n3. By date created");

        Integer selectedOption = in.nextInt();

        if(selectedOption == 99){
            return;
        }

        while(selectedOption > 3 || selectedOption < 1){
            System.out.println("Select a valid option to continue e.g 1");
            System.out.println("Or enter 99 to exit");
            selectedOption = in.nextInt();
            if(selectedOption == 99){
                return;
            }
        }

        switch (selectedOption) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByDetails();
                break;
            case 3:
                searchByDateCreated();
                break;
            default:
                System.out.println("Invalid number");
                break;
        }

    }

    private void searchByName(){
        System.out.println("Please provide the name you would like to search for: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        Bag<Todo> searchResults = todoService.searchByName(name);
        System.out.println("Here are your search results");
        System.out.println(searchResults.size() + " results found");

        for(Todo todo: searchResults){
            System.out.println(todo);
        }
    }

    private void searchByDetails(){
        System.out.println("Please provide the details you would like to search for: ");
        Scanner in = new Scanner(System.in);
        String details = in.nextLine();

        Bag<Todo> searchResults = todoService.searchByDetails(details);
        System.out.println("Here are your search results");
        System.out.println(searchResults.size() + " results found");

        for(Todo todo: searchResults){
            System.out.println(todo);
        }
    }

    private void searchByDateCreated(){
        System.out.println("Please provide the number of days ago you would like to search for: ");
        Scanner in = new Scanner(System.in);
        Integer numberOfDays = in.nextInt();

        Bag<Todo> searchResults = todoService.searchByDateCreated(numberOfDays);
        System.out.println("Here are your search results");
        System.out.println(searchResults.size() + " results found");

        for(Todo todo: searchResults){
            System.out.println(todo);
        }
    }

    private Boolean checkIfLoggedIn(){
        if(this.loggedInUser == null || !this.loggedInUser.getIsLoggedIn()){
            System.out.println("You have to be logged in to perform this action");
            return false;
        }
        return true;
    }
}
