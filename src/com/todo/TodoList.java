package com.todo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TodoList {
    private Map<String, String> todos = new HashMap<String, String>();

    public void addItem(String item, String status) {
        todos.put(item, status);
    }

    public String getItemStatus(String item) {
        return todos.get(item);
    }

    public String doItem(String item) {
        return todos.put(item, "Done");
    }

    public void removeItem(String item) {
        todos.remove(item);
    }

    public Map<String, String> getItems() {
        return Collections.unmodifiableMap(todos);
    }

}
