package LabProgram;

abstract class Vaccine {
 int age;
 String nationality;

 public Vaccine(int age, String nationality) {
     this.age = age;
     this.nationality = nationality;
 }

 public void firstDose() {
     if (nationality.equals("Indian") && age >= 18) {
         System.out.println("First dose administered. Please pay ₹250.");
     } else {
         System.out.println("Eligibility criteria not met for first dose.");
     }
 }

 public void secondDose() {
     System.out.println("Second dose administered.");
 }

 abstract void boosterDose();
}

class VaccinationSuccessful extends Vaccine {

 public VaccinationSuccessful(int age, String nationality) {
     super(age, nationality);
 }

 void boosterDose() {
     System.out.println("Booster dose administered.");
 }
}

public class VaccineAbstractDemo {
 public static void main(String[] args) {

	 VaccinationSuccessful user = new VaccinationSuccessful(10, "Indian");
     user.firstDose(); 
     user.secondDose();
     user.boosterDose();
 }
}