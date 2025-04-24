package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                textArea.setText(content.toString());
                textEditor.currentFile = file;
                textEditor.setTitle("Simple Text Editor - " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,
                        "Error opening file: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                textEditor.currentFile = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                    writer.write(textArea.getText());
                    textEditor.setTitle("Simple Text Editor - " + textEditor.currentFile.getName());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor,
                            "Error saving file: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
                textEditor.setTitle("Simple Text Editor - " + textEditor.currentFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,
                        "Error saving file: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        //TODO
    }
}
