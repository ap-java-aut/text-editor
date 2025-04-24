package editor;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
                textArea.setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Sorry, we can't open this file. Please check the format and try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "Failed to save the file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            try (FileWriter writer = new FileWriter(textEditor.currentFile)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Failed to save the file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textEditor.currentFile = null;
        textArea.setText("");
    }
}