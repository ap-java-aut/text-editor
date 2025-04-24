package editor;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
    private JTextArea textEditor;
    private File currentFile;

    public FileManager (JTextArea textEditor){
        this.textEditor = textEditor;
    }

    public void openFile (){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            File selectFile = fileChooser.getSelectedFile();

            try {
                Scanner scannerFile = new Scanner(selectFile);

                while ( scannerFile.hasNextLine()){
                    System.out.println(scannerFile.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        else{
            System.out.println("File not choose");
        }
    }
    // Method to save the current content to a file
    public void saveFile(){
        if(currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                currentFile = fileChooser.getSelectedFile();
            }
        }

        try {
            PrintStream printFile = new PrintStream(currentFile);
            printFile.println(textEditor.getText());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void newFile (){
        textEditor.setText(""); // Clear the editor's content
        currentFile = null; // Reset the current file reference
    }



}
