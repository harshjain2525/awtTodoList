package com.todo;

import java.awt.*;
import java.awt.event.*;

public class AwtTodoList {
    private Frame window = new Frame("AwtTodoList");
    private Button addItem = new Button("Add Item");
    private Button removeItem = new Button("Remove");
    private TextField textField = new TextField(40);
    private Label label = new Label("Item:");
    private Choice statusChoice = new Choice();
    private Panel actionPanel = new Panel();
    private List resultList = new List(20, false);
    private TodoList todoList = new TodoList();

    public AwtTodoList() {
        initStatusChoice();
        initActionPanel();
        initResultList();

        window.setResizable(false);
        window.setSize(600, 300);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        window.setLayout(new BorderLayout());

        window.add(statusChoice, BorderLayout.NORTH);
        window.add(resultList, BorderLayout.CENTER);
        window.add(actionPanel, BorderLayout.SOUTH);
    }

    private void updateStatusView() {
        statusChoice.select(todoList.getItemStatus(resultList.getSelectedItem()));
    }

    private void initStatusChoice() {
        for (Status title : Status.values()) {
            statusChoice.add(title.getTitle());
        }

        statusChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = resultList.getSelectedIndex();
                if (index >= 0) {
                    todoList.setStatus(resultList.getSelectedItem(), statusChoice.getSelectedItem());
                }
            }
        });
    }

    private void initActionPanel() {
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = textField.getText();
                todoList.addItem(item, Status.WAITING.getTitle());
                resultList.add(item);
                textField.setText("");
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = resultList.getSelectedIndex();
                if (index >= 0) {
                    String item = resultList.getSelectedItem();
                    todoList.removeItem(item);
                    resultList.remove(index);
                }
            }
        });

        actionPanel.add(label);
        actionPanel.add(textField);
        actionPanel.add(addItem);
        actionPanel.add(removeItem);
    }

    private void initResultList() {
        resultList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatusView();
            }
        });

        resultList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateStatusView();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void run() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        AwtTodoList awtUrlCrawler = new AwtTodoList();
        awtUrlCrawler.run();
    }

}
