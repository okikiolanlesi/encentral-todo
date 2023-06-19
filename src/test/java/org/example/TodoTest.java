package org.example;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.map.HashedMap;
import org.example.entities.Todo;
import org.example.entities.User;
import org.example.services.TodoService;
import org.example.services.UserService;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    public static UserService userService;
    public static TodoService todoService;

    public static HashedMap<String, User> users = new HashedMap<String, User>();
    public static Bag<Todo> todos = new HashBag<Todo>();

    @BeforeAll
    static void setUp() {
        userService = new UserService(users);
        todoService = new TodoService(todos);
        userService.register("test", "test");
        todoService.create("test", "test", "test");
    }


    @Test
    public void registerUser(){
        String response = userService.register("test 2", "test 2");
        assertEquals("User registered successfully", response);
    }

    @Test
    public void loginUser(){
        String response = userService.login("test", "test");
        assertEquals("User logged in successfully successfully", response);
    }

    @Test
    public void addTodo(){
        String response = todoService.create("test", "test", "test");
        assertEquals("Todo added successfully", response);
    }

    @Test
    public void getAllTodo(){
        Bag<Todo> response = todoService.getAll();
        assertTrue(response instanceof Bag, "response is not a bag");
    }

    @Test
    public void getAllActiveTodos(){
        Bag<Todo> response = todoService.getAllActive();
        assertTrue(response instanceof Bag, "response is not a bag");
    }

    @Test
    public void getAllCompleteTodos(){
        Bag<Todo> response = todoService.getAllCompleted();
        assertTrue(response instanceof Bag, "response is not a bag");
    }

    @Test
    public void search(){
        Bag<Todo> response = todoService.searchByName("test");
        assertTrue(response instanceof Bag, "response is not a bag");
    }


}
