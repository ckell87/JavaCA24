/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaca24;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author under
 */
public class JavaCA24 {

    public static String validateName(String name) {// student name validation method
      if (name.contains(" ")) {
         int placeHold = name.indexOf(' ');
         String firstName = name.substring(0, placeHold);
         String lastName = name.substring(placeHold+1, name.length());

      if (firstName.matches("[A-Za-z]+") && lastName.matches("[A-Za-z0-9]+")) {
            return lastName.trim();// remove any white space form start and end of name
        }
      else {
            System.out.println(name + " is not in the correct format required");
        }
    } else {
            System.out.println(name + " does not contain a space between first and second name");
    }

    return "";
}
    private static String validateWorkload(String workload) {// workload validation method
       String name = "";
        if (workload.matches("[0-9]+")){
         int workloadNum = Integer.parseInt(workload);
          switch(workloadNum){
            case 1:
               return "Very Light";
            case 2:
               return "Light";
            case 3:
            case 4:    
            case 5:    
               return "Part Time";
            case 6:
            case 7:    
            case 8:  
               return "Full Time";
          default:
               System.out.println("Please choose a number from 1 to 8 for workload ");
        }
        }
        else{
            System.out.println("Please choose a number between 1 and 8 for workload ");
        }
        return null;
    }        
     
    private static String validateStudentNumber(String studentNum) { //method to validate student number thats read in from file.
       
         int studentNumLength = studentNum.length();
        String studentYear = studentNum.substring(0, 2);
        int studentYearCheck = Integer.parseInt(studentYear);
        
         if (studentNum.length()< 6){
             System.out.println("Student number must be at least six characters ");
         }
        
         if (studentYearCheck < 20 || studentYearCheck > 24){          //validate the first 2 integers of the student number
             System.out.println("Student number must start with year student started ie from 20-24 ");
             
         }
        
          String courseType = studentNum.replaceAll("[^A-Za-z]", "");// find any text after the first two digits
          //System.out.println(courseType);
         if (courseType.length()<2 || courseType.length() > 3 )  {
             System.out.println("student number must have text abbreviation stating course type, 2-3 characters long");
             
         }
         
         String findCount = studentNum.replaceAll("[^0-9]", "");// find any digits after the first two digits.
         String foundCount = findCount.substring(2);
                 //System.out.println(foundCount);
        int num = Integer.parseInt(foundCount);
        
         if (num >200){                   //validate the Stundents number ie. less than 200
            System.out.println("the remaining digits of the student number must not exceed 200");
            
         }
        return studentNum;
    }  
    public static void main(String[] args) {
        String inputFile = "\\Users\\under\\Documents\\NetBeansProjects\\JavaCA24\\students.txt";  // input file address
        String outputFile = "\\Users\\under\\Documents\\NetBeansProjects\\JavaCA24\\status.txt";  //  output file address

          try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
               BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
               Scanner sc = new Scanner(System.in)) {

            System.out.println("Please choose either S for standard input or M for manual input");
            String userInput = sc.nextLine().toUpperCase(); //menu for standard or manual input

         switch (userInput) {
            case "S":
                String line = "";
                while ((line = br.readLine()) != null) { //loop through once in groups of three
                    String name = validateName(line);// read first line
                    String workLoad = validateWorkload(br.readLine());// read second line
                    String studentNum = validateStudentNumber(br.readLine());// read third line

                if (name != null && workLoad != null && studentNum != null) { //check for empty file
                        bw.write(studentNum+"-"+name);
                        bw.newLine();
                        bw.write(workLoad);
                        bw.newLine();

                    } 
                }
                        bw.close();
                break;
           case "M":// manual input 
                     System.out.println("Please enter full name of student with a space between first name and last name.");
                     String name = validateName(sc.nextLine().toUpperCase());
                     System.out.println("Please enter students workload dictated by numbers 1-8 inclusive");
                     String workLoad = validateWorkload(sc.nextLine());
                     System.out.println("Please enter student number. This must be at least 6 characters in the correct format: \n First 2 digits must be student year start date 2020-current year \n The following 2-3 charters must be course abreviation \n The following digits must be student number count from 0-200");
                     String studentNum = validateStudentNumber(sc.nextLine().toUpperCase());
                    
                if (name != null && workLoad != null && studentNum != null) {
                        bw.write(studentNum+"-"+name);
                        bw.newLine();
                        bw.write(workLoad);
                        bw.newLine();
                }   
                       bw.close();
                break;
                 default:
                    System.out.println("Invalid input. Please choose either S for standard input or M for manual input");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    
   