import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TextEditor implements ActionListener {
    //5.1 implements ActionListener
    JFrame frame;//1.1
    JMenuBar menuBar;//2.1
    JMenu file,edit;//3.1
    JMenuItem newFile,openFile,saveFile;//3.4
    JMenuItem cut,copy,paste,selectAll,close;//3.4
    JTextArea textArea;//4.1
    TextEditor(){
        frame=new JFrame();//1.2
        frame.setBounds(100,100,800,500);//1.3
        menuBar=new JMenuBar();//2.2
        frame.setJMenuBar(menuBar);//2.3
        file=new JMenu("File");//3.2
        edit=new JMenu("Edit");//3.2
        menuBar.add(file);//3.3
        menuBar.add(edit);//3.3
        newFile=new JMenuItem("New");//3.5
        openFile=new JMenuItem("Open");//3.5
        saveFile=new JMenuItem("Save");//3.5
        newFile.addActionListener(this);//5.2
        openFile.addActionListener(this);//5.2
        saveFile.addActionListener(this);//5.2
        file.add(newFile);//3.6
        file.add(openFile);//3.6
        file.add(saveFile);//3.6
        cut=new JMenuItem("Cut");//3.7
        copy=new JMenuItem("Copy");//3.7
        paste=new JMenuItem("Paste");//3.7
        selectAll=new JMenuItem("SelectAll");//3.7
        close=new JMenuItem("Close");//3.7
        cut.addActionListener(this);//5.2
        copy.addActionListener(this);//5.2
        paste.addActionListener(this);//5.2
        selectAll.addActionListener(this);//5.2
        close.addActionListener(this);//5.2
        edit.add(cut);//3.8
        edit.add(copy);//3.8
        edit.add(paste);//3.8
        edit.add(selectAll);//3.8
        edit.add(close);//3.8
        textArea=new JTextArea();//4.2
        frame.add(textArea);//4.3
        frame.setVisible(true);//1.4 * write the setVisible at last only because if we write before then
                               //it makes the frame visible before menuBar,menus and menuItems are added
                               // then it needs to be refreshed to show the added features

    }
    public static void main(String[] args) {
     TextEditor editor=new TextEditor();
    }

    @Override //5.3
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==cut) textArea.cut();
        if(actionEvent.getSource()==copy) textArea.copy();
        if(actionEvent.getSource()==paste) textArea.paste();
        if(actionEvent.getSource()==selectAll) textArea.selectAll();
        if(actionEvent.getSource()==close) System.exit(0);
        if(actionEvent.getSource()==newFile) {
            TextEditor editor=new TextEditor();
        }
        //Step 6 functionality to open menuItem
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser=new JFileChooser();
            int selectedOption=fileChooser.showOpenDialog(null);
            if(selectedOption==JFileChooser.APPROVE_OPTION){
                try{
                    BufferedReader fileReader=new BufferedReader(new FileReader(fileChooser.getSelectedFile().getPath()));
                    String ans="",intermediate="";
                    while((intermediate=fileReader.readLine())!=null) ans+= intermediate+"\n";
                    fileReader.close();
                    textArea.setText(ans);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        // Step 7= functionality to save menuItem
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser();
            int selectedOption=fileChooser.showSaveDialog(null);
            if(selectedOption==JFileChooser.APPROVE_OPTION){
                try{
                    String filePath=fileChooser.getSelectedFile().getAbsolutePath()+".txt";
                    BufferedWriter fileWriter=new BufferedWriter(new FileWriter(filePath));
                    textArea.write(fileWriter);
                    fileWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}