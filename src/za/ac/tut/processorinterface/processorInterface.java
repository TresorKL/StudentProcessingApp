/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.processorinterface;

import java.io.File;
import za.ac.tut.student.MarksException;
import za.ac.tut.student.Student;
import za.ac.tut.student.StudentNumException;

/**
 *
 * @author Rentex
 */
public interface processorInterface {
    
    public void StoreDataInTheFile(File newMarksFile,Student [] students);
    public void readStudentsData(File newMarksFile);
    public void populateArrayWithFileData(File newMarksFile,Student [] students);
    public void GetDataOfSpecificStudent(int isFound, Student[]students);
    public void ChangeDataOfSpecificStudent(File newMarksFile,int isFound, Student [] students);
    public double DetermineAverage(Student [] students);
    public Student determineHighestMark(Student [] students);
    public int searchInTheArray(Student [] students,int target);
}
