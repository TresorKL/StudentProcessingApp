/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigntwo;

import java.awt.HeadlessException;
import java.io.File;
import javax.swing.JOptionPane;
import za.ac.tut.student.MarksException;
import za.ac.tut.student.Student;
import za.ac.tut.student.StudentNumException;
import za.ac.tut.student.processor.studentProcessor;

/**
 *
 * @author Rentex
 */
public class AssignTwo {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
    // asking the teacher to specify how many students he has 
    String numOfStdudent = JOptionPane.showInputDialog(null,"ENTER THE NUMBER OF STUDENT YOU HAVE","GETTING NUM OF ROWS",JOptionPane.INFORMATION_MESSAGE);
    
    // the specified number of students is used as the size of our array
    int arraySize = Integer.parseInt(numOfStdudent);
     
    
    // the manu of all diffents tasks that this app can do
    String optStr =  JOptionPane.showInputDialog(null,"1 : STORE DATA IN THE FILE"+"\n"+
                                                      "2 : DISPLAY DATA FROM THE FILE"+"\n"+
                                                      "3 : GET DATA OF SPECIFIC STUDENT"+"\n"+
                                                      "4 : CHANGE DATA OF SPECIFIC STUDENT"+"\n"+
                                                      "5 : DISPLAY THE AVERAGE AND HIGHEST MARK"+"\n"+
                                                      "6 : END PROGRAM","OPTIONS",JOptionPane.INFORMATION_MESSAGE);
    int option = Integer.parseInt(optStr);
    
  try{ 
              
    
    
   while(option!=6 ){ 
       // this is the file we are working with and its location in my camputer
       File newMarksFile = new File("C:\\Users\\Rentex\\Desktop\\jar\\newMarksFile.txt");
       Student [] students  = new Student[arraySize];
       studentProcessor ps = new studentProcessor(); 
        String targetStr ="";
        int target =0;
   
     
        
        
        switch (option) {
            case 1:
                // ps.StoreDataInTheFile(newMarksFile, students);
                ps.StoreDataInTheFile(newMarksFile, students);
                break;
            case 2:
                ps.readStudentsData( newMarksFile);
                break;
            case 3:
            
                
                    ps.populateArrayWithFileData(newMarksFile, students);
                    targetStr = JOptionPane.showInputDialog(null, "PLEASE ENTER A SPECIFIC STUDENT NUMBER TO SERACH");
                   target = Integer.parseInt(targetStr);
                    int isFound = ps.searchInTheArray( students, target);
                    if(isFound <0){
                        
                        JOptionPane.showMessageDialog(null,target+" IS NOT FOUND");
                    }else{
                        
                        
                        ps.GetDataOfSpecificStudent(isFound, students);
                    }   
                    break;
                
            case 4:
                
                
                    targetStr = JOptionPane.showInputDialog(null, "PLEASE ENTER A SPECIFIC STUDENT NUMBER TO CHANGE DATA");
                    target = Integer.parseInt(targetStr);
                    ps.populateArrayWithFileData(newMarksFile, students);
                    
                    isFound = ps.searchInTheArray( students, target);
                    if(isFound <0){
                        
                        JOptionPane.showMessageDialog(null,target+" IS NOT FOUND");
                    }else{
                     
                        ps.ChangeDataOfSpecificStudent(newMarksFile, isFound, students);
                    }   
                    break;
                
            case 5:
                ps.populateArrayWithFileData(newMarksFile, students);
                
                Student student = ps.determineHighestMark(students);
                double average = ps.DetermineAverage(students);
                
                JOptionPane.showMessageDialog(null, "AVERAGE "+average+"\n\n"+
                                                     "HIGHEST MARK: "+student.getMarks()+"\n"+
                                                      "STUDENT "+student.getStudentNum());
                break;
            default:
                break;
        }
        
        
        
        
        optStr =  JOptionPane.showInputDialog(null,"1 : STORE DATA IN THE FILE"+"\n"+
                                                      "2 : DISPLAY DATA FROM THE FILE"+"\n"+
                                                      "3 : GET DATA OF SPECIFIC STUDENT"+"\n"+
                                                      "4 : CHANGE DATA OF SPECIFIC STUDENT"+"\n"+
                                                      "5 : DISPLAY THE AVERAGE AND HIGHEST MARK"+"\n"+
                                                      "6 : END PROGRAM","OPTIONS",JOptionPane.INFORMATION_MESSAGE);
       option = Integer.parseInt(optStr);
  }
   
   
   }catch(HeadlessException | NumberFormatException ex){
          System.out.print(ex.getMessage());
        }
    

     
  }
    
}
