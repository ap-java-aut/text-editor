package editor;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileManager {
    private static int number = 10000;

    public static void openFile(TextEditor textEditor, JTextArea textArea){
        textArea.setText("");
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;
            try {
                Scanner scanner = new Scanner(textEditor.currentFile);
                String line;
                while ((line = scanner.nextLine()) != null){
                    textArea.append(line + "\n");
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintStream printStream = new PrintStream(fileChooser.getSelectedFile());
                    printStream.print(textArea.getText());
                    printStream.close();
                } catch (FileNotFoundException e) {
                    e.getStackTrace();
                }
            }
        }
        else{
            try {
                PrintStream printStream = new PrintStream(textEditor.currentFile);
                printStream.print(textArea.getText());
                printStream.close();
            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        File file = new File("newfile" + number + ".txt");
        number++;
        try {
            if(file.createNewFile()){
                textEditor.currentFile = file;
                System.out.println("file created: " + file.getName());
                textArea.setText("");
            }else{
                System.out.println("file already exists.");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
