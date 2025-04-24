package editor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
                //TODO
            }
        }
        else{
            //TODO
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        //TODO
    }
}
