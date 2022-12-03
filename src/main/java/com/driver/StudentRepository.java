package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String,Student> listOfStudents = new HashMap<>();
    HashMap<String,Teacher> listOfTeachers = new HashMap<>();
    HashMap<String, List<String>> teacherStudentPair = new HashMap<>();

    public void addStudent(Student student){
        listOfStudents.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        listOfTeachers.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        if(listOfTeachers.containsKey(student) && listOfTeachers.containsKey(teacher)){
            listOfStudents.put(student,listOfStudents.get(student));
            listOfTeachers.put(teacher,listOfTeachers.get(teacher));
            List<String> students = new ArrayList<>();
            if(teacherStudentPair.containsKey(teacher)){
                students = teacherStudentPair.get(teacher);
            }
            students.add(student);
            teacherStudentPair.put(teacher,students);
        }
    }

    public Student getStudentByName(String studentName){
        return listOfStudents.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName){
        return listOfTeachers.get(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        List<String> studentsName = new ArrayList<>();
        if(teacherStudentPair.containsKey(teacherName)){
            studentsName = teacherStudentPair.get(teacherName);
        }
        return studentsName;
    }

    public List<String> getAllStudents(){
        List<String> students = new ArrayList<>();
        for(Student studentName : listOfStudents.values()){
            students.add(studentName.getName());
        }
        return students;
    }

    public void deleteTeacherByName(String teacherName){
        List<String> teacher = new ArrayList<>();

        if(listOfTeachers.containsKey(teacherName)){
            listOfTeachers.remove(teacherName);
        }
        if(teacherStudentPair.containsKey(teacherName)){
            teacher = teacherStudentPair.get(teacherName);
            for(String students : teacher){
                if(listOfStudents.containsKey(students)){
                    listOfStudents.remove(students);
                }
            }
            teacherStudentPair.remove(teacherName);
        }
    }

    public void deleteAllTeachers(){
        HashSet<String> studentsSet = new HashSet<>();

        for (String teacher : teacherStudentPair.keySet()){
            for(String movie : teacherStudentPair.get(teacher)){
                studentsSet.add(movie);
            }
        }

        for(String students : studentsSet){
            if(listOfStudents.containsKey(students)){
                studentsSet.remove(students);
            }
        }
    }
}
