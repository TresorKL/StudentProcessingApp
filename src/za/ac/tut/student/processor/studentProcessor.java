/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.student.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import za.ac.tut.processorinterface.processorInterface;
import za.ac.tut.student.MarksException;
import za.ac.tut.student.Student;
import za.ac.tut.student.StudentNumException;

/**
 *
 * @author Rentex
 */
public class studentProcessor implements processorInterface {
    
    public studentProcessor(){
        
    }

    @Override
    public void StoreDataInTheFile(File newMarksFile,Student [] students) {
        
        boolean isValid = true;

        for(int i=0; i<students.length;i++){
           
         Student student = new Student();
            
           //making sure the user enter correct students numbers 
            do{
                String stdNumStr =JOptionPane.showInputDialog(null, "ENTER STUDENT NUM", "STUDENT "+(i+1),JOptionPane.INFORMATION_MESSAGE); 
                int studentNum = Integer.parseInt(stdNumStr);
                
                 if(studentNum>=10000 &&studentNum <=99999){
                     isValid = true;
                     student.setStudentNum(studentNum);
                     }else{
                      isValid = false;
                    JOptionPane.showMessageDialog(null,"INVALID STUDENT NUM more/less than 5 digits");
                    
                }
                 
            }while(!isValid);
            
             //making sure the user enter correct Marks obtained 
           do{
                String markStr =JOptionPane.showInputDialog(null, "ENTER MARK OBTAINED", "STUDENT "+(i+1),JOptionPane.INFORMATION_MESSAGE);
                int marks = Integer.parseInt(markStr);
                if(marks>=0 &&marks<=110){
                      isValid = true;
                   student.setMarks(marks);
                   
                 
                }else{
                     isValid = false;
                    JOptionPane.showMessageDialog(null,"INVALID MARK NOT BETTWEEN 0 AND 110");
                    
                }
           }while(!isValid);
           
           // storing all data in an array
           students[i] = student;
        }
        
        
        // taking data from the array to the file(writting)
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(newMarksFile));
                String data ="";
                for(int j=0; j<students.length;j++ ){
                data = data +(students[j].getStudentNum()+"-"+students[j].getMarks()+"-"+students[j].determinePercentage()).concat("\n");
                    
                }
                bw.write(data);
                bw.close();
                
            }catch(Exception ex){
                
            }
    }

    @Override
    public void readStudentsData(File newMarksFile) {
           String data, record="";
    
    try{
    BufferedReader br = new BufferedReader(new FileReader(newMarksFile));
    data=br.readLine();
    //reading the file
    while(data!=null){
        record = record +(data).concat("\n");
        
        data=br.readLine();
    }
    // display each record found in file
    JOptionPane.showMessageDialog(null, record);
    
    
    br.close();
    
    }catch(Exception ex){
        System.out.print(ex.getMessage());
    }
           
    }

   
    @Override
    public void ChangeDataOfSpecificStudent(File newMarksFile,int isFound, Student [] students) {
        
         boolean isValid = true;
        
         // inputing new data for a specific student 
        do{
        String Markstr = JOptionPane.showInputDialog(null,"ENTER NEW MARK OBTAINED","STUDENT "+students[isFound].getStudentNum(),JOptionPane.INFORMATION_MESSAGE);
                   int StudenMarks = Integer.parseInt(Markstr);
                      if(StudenMarks>=0 && StudenMarks<=110){
                          students[isFound].setMarks(StudenMarks);
                          isValid = true;
                      }else{
                          JOptionPane.showMessageDialog(null, "INVALID INPUT");
                          isValid = false;
                      }
        }while(!isValid);
       
        String data ="";
        
       for(int i=0; i<students.length; i++){
           
         data = data +(students[i].getStudentNum()+"-"+students[i].getMarks()+"-"+students[i].determinePercentage()).concat("\n");
       }
           data= data.concat("\n\n");
           
      // updateing specific data of the student in the file
       try{
       BufferedWriter bw = new BufferedWriter(new FileWriter(newMarksFile));
       bw.write(data);
       bw.close();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
         
    }

    @Override
    public double DetermineAverage(Student [] students) {
       double average =0;
       
       for(int i=0; i< students.length;i++){
           
           average += (students[i].getMarks())/students.length;
       }
       return average;
    }

    @Override
    public Student determineHighestMark(Student [] students) {
       
        for(int i=0; i< students.length;i++){
            
            for(int j=i+1; j< students.length;j++){
            
            if(students[i].getMarks() <students[j].getMarks()){
                
               students[i] = students[j];
               students[j] = students[i];
            }
        }
            
        }
        return students[0];
    }
// this method helps to take data from the file and put in the array so it can be manipuleted
    @Override
    public void populateArrayWithFileData(File newMarksFile,Student [] students) {
        
    // in this method we extract data in file and put in the file     
        try {
            BufferedReader br = new BufferedReader(new FileReader(newMarksFile));
            String record = "";
            int recordNum =0;
            
            while((record= br.readLine()) != null && recordNum <students.length){
                Student student = new Student();
                String[] allData = record.split("-");
                int studentNum = Integer.parseInt(allData[0]);
                 int mark = Integer.parseInt(allData[1]);
                 
                 student.setStudentNum(studentNum);
                 student.setMarks(mark);
                 
                
                students[recordNum]= student;
               
                recordNum++;
              
            }
            
            br.close();
            
        } catch (Exception ex) {
           System.out.println(ex.getMessage());
        } 
        
    }

    @Override
    public void GetDataOfSpecificStudent(int isFound, Student[] students) {
      
        JOptionPane.showMessageDialog(null,"STUDENT NUM: "+students[isFound].getStudentNum()+"\n"+
                                           "MARK OBTAINED: "+students[isFound].getMarks()+"\n"+
                                            "PERCENYAGE: "+students[isFound].determinePercentage());
        
    }

    @Override
    public int searchInTheArray(Student[] students,int target) {
    
        
        int x =0;
        int isFound =1;
        
        while(x<students.length && target != students[x].getStudentNum()){
            x++;
        }
        
        if(x == students.length){
            isFound =-1;
        }else{
             isFound = x;
        }
        
        return isFound;
    }
    
}
