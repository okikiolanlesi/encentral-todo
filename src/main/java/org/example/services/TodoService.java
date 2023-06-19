package org.example.services;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.bag.HashBag;
import org.example.entities.Todo;

import org.apache.commons.collections4.Predicate;

import java.time.LocalDateTime;

public class TodoService {
    Bag<Todo> todos;
    public TodoService(Bag<Todo> todos){
        this.todos = todos;
    }

    public String create(String name, String details, String ownerEmail){
        todos.add(new Todo(name, details, ownerEmail));

        return "Todo added successfully";
    }

    public Bag<Todo> getAll(){
        return todos;
    }

    public Bag<Todo> getAllActive(){

        Predicate<Todo> predicate = new Predicate<Todo>() {
            @Override
            public boolean evaluate(Todo todo) {
                return todo.getStatus().equals("active");
            }
        };

        Bag<Todo> filteredTodos = CollectionUtils.select(todos, predicate, new HashBag<Todo>());

        return filteredTodos;
    }

    public Bag<Todo> getAllCompleted(){

        Predicate<Todo> predicate = new Predicate<Todo>() {
            @Override
            public boolean evaluate(Todo todo) {
                return todo.getStatus().equals("completed");
            }
        };

        Bag<Todo> filteredTodos = CollectionUtils.select(todos, predicate, new HashBag<Todo>());

        return filteredTodos;
    }

    public String delete(String identifier){

        boolean removed = todos.removeIf(existingItem -> existingItem.getIdentifier().equals(identifier));

        if (!removed) {
            return "Todo with identifier "+ identifier +" not found";
        }

        return "Todo with identifier "+ identifier +" deleted successfully.";
    }

    public String updateName(String identifier, String newName){

        Boolean found = false;

        for (Todo todo : todos) {
            if (todo.getIdentifier().equals(identifier)) {
                found = true;
                todo.setName(newName);
                break;
            }
        }
        if(!found){
            return "No todo found with that identifier";
        }

        return "Todo name updated successfully";
    }

    public String updateDetails(String identifier, String newDetails){

        Boolean found = false;

        for (Todo todo : todos) {
            if (todo.getIdentifier().equals(identifier)) {
                found = true;
                todo.setDetails(newDetails);
                break;
            }
        }
        if(!found){
            return "No todo found with that identifier";
        }

        return "Todo details updated successfully";
    }

    public String updateStatus(String identifier, String newStatus){

        Boolean found = false;

        for (Todo todo : todos) {
            if (todo.getIdentifier().equals(identifier)) {
                found = true;
                todo.setStatus(newStatus);
                break;
            }
        }

        if(!found){
            return "No todo found with that identifier";
        }

        return "Todo status updated successfully";
    }

    public Bag<Todo> searchByName(String name){

        Predicate<Todo> predicate = new Predicate<Todo>() {
            @Override
            public boolean evaluate(Todo todo) {
                return todo.getName().equalsIgnoreCase(name);
            }
        };

        Bag<Todo> filteredTodos = CollectionUtils.select(todos, predicate, new HashBag<Todo>());

        return filteredTodos;
    }

    public Bag<Todo> searchByDetails(String details){

        Predicate<Todo> predicate = new Predicate<Todo>() {
            @Override
            public boolean evaluate(Todo todo) {
                return todo.getDetails().equalsIgnoreCase(details);
            }
        };

        Bag<Todo> filteredTodos = CollectionUtils.select(todos, predicate, new HashBag<Todo>());

        return filteredTodos;
    }

    public Bag<Todo> searchByDateCreated(Integer numberOfDays){

        LocalDateTime daysAgo = LocalDateTime.now().minusDays(numberOfDays);

        Predicate<Todo> predicate = new Predicate<Todo>() {
            @Override
            public boolean evaluate(Todo todo) {
                return todo.getDateCreated().isAfter(daysAgo);
            }
        };

        Bag<Todo> filteredTodos = CollectionUtils.select(todos, predicate, new HashBag<Todo>());

        return filteredTodos;
    }

}
