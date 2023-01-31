package com.engeto.lesson11backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class Lesson11Controller {

    TodoItemService todoItemService;

    public Lesson11Controller() throws SQLException {
        todoItemService = new TodoItemService();
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/helloWithName")
    public String helloWithName(@RequestParam(value = "jmeno", required = false) String jmeno){
        if (jmeno == null){
            return "Ahoj, skoda, ze nevim tvoje jmeno";
        }
        return "Ahoj " + jmeno + "!";
    }

    @GetMapping("testAdd")
    public TodoItem getItem(){
        TodoItem todoItem = new TodoItem("Uvarit caj", false);
        todoItem.setId(1L);
        todoMap.put(1L, todoItem);
        return todoItem;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleError(Exception e){
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
//        return e.getMessage();
    }

    @GetMapping("/error")
    public TodoItem testError(@RequestParam(value = "hodnota", required = false) String hodnota) throws Exception {
        throw new Exception("Chyba "+hodnota);
    }

    Map<Long, TodoItem> todoMap = new HashMap<>();
    Long seq = 0L;

    @GetMapping("/todo")
    public Collection<TodoItem> getAllItems() throws SQLException {
        //return todoMap.values();
        return todoItemService.getAllTodoItems();
    }

    @GetMapping("/todo/{id}")
    public TodoItem getItemById(@PathVariable("id") Long id) throws SQLException {
//        if (!todoMap.containsKey(id)){
//            throw new Exception("Some Exception");
//        }
//        return todoMap.get(id);
        return todoItemService.getItem(id);
    }

    @PostMapping("/todo")
    public TodoItem postItem(@RequestBody TodoItem todoItem) throws SQLException {

//        //Nova hodnota ze sekvence
//        seq++;
//        Long id = seq;

        //Nastaveni ID pro dalsi pouziti
        Long generateId = todoItemService.saveNewItem(todoItem);

//        System.out.println("newly generated ID: "+ generateId);

        todoItem.setId(generateId);
        //todoMap.put(id, todoItem);

        return todoItem;
    }

    @PutMapping("/todo2/{id}")
    public void putItemFromTodo(@PathVariable("id") Long id, @RequestBody TodoItem todoItem) throws SQLException {
//        todoItemService.setItemAsDone(id);
//        System.out.println("");
        todoItemService.changeItemFromTodo(id, todoItem);
    }

    @PutMapping("/todo/{id}")
    public void putItem(@PathVariable("id") Long id) throws SQLException {
        todoItemService.setItemAsDone(id);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteItem(@PathVariable("id") Long id) throws SQLException {
//        todoMap.remove(id);
        todoItemService.deleteItem(id);
    }

}
