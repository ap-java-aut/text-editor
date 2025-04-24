package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String string;

                while ((string = reader.readLine()) != null) {
                    textArea.append(string + "\n");
                }

                textEditor.currentFile = file;
                textEditor.setTitle("Text Editor - " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,
                        "File reading error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = file;
                    textEditor.setTitle("Text Editor - " + file.getName());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor,
                            "File saving error: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,
                        "File saving error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
        textEditor.setTitle("Text Editor");
    }
}