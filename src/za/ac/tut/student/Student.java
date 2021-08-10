/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.student;

import za.ac.tut.studentinterface.studentInterface;

/**
 *
 * @author Rentex
 */
public class Student implements studentInterface{
    
    private int studentNum;
    private int marks;

    public Student(){
    }
    public Student(int studentNum,int marks ){
        
        this.studentNum = studentNum;
        this.marks = marks;
        
        
    }
    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum){
        
       
        this.studentNum = studentNum;
        
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks){
        
            this.marks = marks;
      
    }
    
    @Override
    public double determinePercentage() {
       double percentage =0;
       
       percentage = (getMarks() * 100)/110;
       
       return percentage;
    }

   
    
    
}
