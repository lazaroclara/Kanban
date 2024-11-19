package com.example.kanban;

import com.example.kanban.view.KanbanGUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class KanbanApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			KanbanGUI kanbanGUI = new KanbanGUI();
			kanbanGUI.setVisible(true);
		});
	}

}
