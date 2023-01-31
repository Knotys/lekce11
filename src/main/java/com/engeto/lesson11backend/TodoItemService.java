package com.engeto.lesson11backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemService {

    Connection connection;

    public TodoItemService() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/todolist",
                "todolist_crud",
                "localhost2002"
        );
    }

    public List<TodoItem> getAllTodoItems() throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM todoitem");

        List<TodoItem> resultList = new ArrayList<>();

        while (resultSet.next()) {
            TodoItem todoItem = extractTodoItemData(resultSet);

            resultList.add(todoItem);
        }

        return resultList;
    }

    private static TodoItem extractTodoItemData(ResultSet resultSet) throws SQLException {
        return new TodoItem(
                resultSet.getLong("id"),
                resultSet.getString("text"),
                resultSet.getBoolean("isCompleted"));
    }

    public Long saveNewItem(TodoItem newItem) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate(
                "INSERT INTO todoitem(text, isCompleted) VALUES ('" + newItem.getItem() +"', " + newItem.getDone() +")",
                Statement.RETURN_GENERATED_KEYS);

        return statement.getGeneratedKeys().getLong("id");
    }

    public void deleteItem(Long itemId) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM * todoitem WHERE id = " + itemId);
    }

    public void setItemAsDone(Long itemId) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("UPDATE todoitem SET isCompleted = true WHERE id = " + itemId);
    }

    public TodoItem getItem(Long itemId) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM todoitem WHERE id = " + itemId);

        if (resultSet.next()) {
            return extractTodoItemData(resultSet);
        }

        return null;
    }

    public void changeItemFromTodo(Long itemId, TodoItem todoItem) throws  SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("UPDATE todoitem SET text = '" + todoItem.getItem() +
                                "', isCompleted = " + todoItem.getDone() + " WHERE id = " + itemId);
    }
}
