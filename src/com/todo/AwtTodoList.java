package com.todo;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtTodoList {
    private Frame window = new Frame("AwtTodoList");
    private Button addItem = new Button("Add Item");
    private Button removeItem = new Button("Remove");
    private Button doItem = new Button("Done");
    private TextField textField = new TextField(20);
    private Label label = new Label("Item:");
    private Label itemStatus = new Label("");
    private Label title = new Label("Todo items:");
    private Panel crawlPanel = new Panel();
    private List resultList = new List(20, false);

    private TodoList todoList = new TodoList();

    private void updateStatusView() {
        itemStatus
                .setText(todoList.getItemStatus(resultList.getSelectedItem()));
    }

    public AwtTodoList() {
        window.setSize(600, 300);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        window.setLayout(new GridLayout(4, 1));

        resultList.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                updateStatusView();
            }

            @Override
            public void focusLost(FocusEvent e) {
                updateStatusView();
            }
        });

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
            public void mousePressed(MouseEvent e) {
                // To change body of implemented methods use File | Settings |
                // File Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // To change body of implemented methods use File | Settings |
                // File Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // To change body of implemented methods use File | Settings |
                // File Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // To change body of implemented methods use File | Settings |
                // File Templates.
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = textField.getText();
                todoList.addItem(item, "Waiting");
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

        doItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = resultList.getSelectedIndex();
                if (index >= 0) {
                    String item = resultList.getSelectedItem();
                    todoList.doItem(item);
                    updateStatusView();
                }
            }
        });

        crawlPanel.add(label);
        crawlPanel.add(textField);
        crawlPanel.add(addItem);
        crawlPanel.add(removeItem);
        crawlPanel.add(doItem);

        window.add(title);
        window.add(itemStatus);
        window.add(resultList);
        window.add(crawlPanel);
    }

    public void run() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        AwtTodoList awtUrlCrawler = new AwtTodoList();
        awtUrlCrawler.run();
    }
}
