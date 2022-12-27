package utilities;


public class Person {
   private String imie;
   private String nazwisko;
   private String email;
   private String ip;

   public Person(String imie, String nazwisko, String email, String ip) {
      this.imie = imie;
      this.nazwisko = nazwisko;
      this.email = email;
      this.ip = ip;
   }

   @Override
   public String toString() {
      String sb = "Person{" + "imie='" + imie + '\'' +
              ", nazwisko='" + nazwisko + '\'' +
              ", email='" + email + '\'' +
              ", ip='" + ip + '\'' +
              '}';
      return sb;
   }

   public String getImie() {
      return imie;
   }

   public String getNazwisko() {
      return nazwisko;
   }

   public String getEmail() {
      return email;
   }

   public String getIp() {
      return ip;
   }
}
