package v1;

import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
 
  private static Scanner scanner = new Scanner(System.in);
  private static GuestsList guestsList;
 
  private static String getLastName() {
    System.out.println("Introduceti numele de familie:");
    return scanner.nextLine();
  }
 
  private static String getFirstName() {
    System.out.println("Introduceti prenumele:");
    return scanner.nextLine();
  }
 
  private static String getEmail() {
    System.out.println("Introduceti email:");
    return scanner.nextLine();
  }
 
  private static String getPhoneNumber() {
    System.out.println("Introduceti numar de telefon");
    return scanner.nextLine();
  }
 
  private static boolean updateParticipantByField(Guest guest) {
 
    String lastName = null; 
    String firstName = null; 
    String email = null;
    String phoneNumber = null;
    boolean validCommand;
 
    do {
      System.out.println("Alege campul de actualizat, tastand: "
          + "\n\t\"1\" - Nume"
          + "\n\t\"2\" - Prenume"
          + "\n\t\"3\" - Email"
          + "\n\t\"4\" - Numar de telefon");
      int option = scanner.nextInt();
      scanner.nextLine(); // ignore the new line
      validCommand = true;
 
      switch (option) {
      case 1:
        lastName = getLastName();
        break;
      case 2:
        firstName = getFirstName();
        break;
      case 3:
        email = getEmail();
        break;
      case 4:
        phoneNumber = getPhoneNumber();
        break;
      default:
        validCommand = false;
        System.out.println("Ati introdus o optiune inexistenta.");
        System.out.println("Mai incercati o data...");
      }
    } while (!validCommand);
 
    return guest.updateField(lastName, firstName, email, phoneNumber);
  }
 
  private static int searchGuestByCriteria() {
 
    String[] fullName = null;
    String email = null; 
    String phoneNumber = null;
    boolean validCommand;
 
    do {
      System.out.println("Alege modul de autentificare, tastand: "
          + "\n\t\"1\" - Nume si prenume"
          + "\n\t\"2\" - Email"
          + "\n\t\"3\" - Numar de telefon");
      int option = scanner.nextInt();
      scanner.nextLine(); // ignore the new line
      validCommand = true;
 
      switch (option) {
        case 1:
          fullName = new String[]{getLastName(), getFirstName()};
          break;
        case 2:
          email = getEmail();
          break;
        case 3:
          phoneNumber = getPhoneNumber();
          break;
        default:
          validCommand = false;
          System.out.println("Ati introdus o optiune inexistenta.");
          System.out.println("Mai incercati o data...");
      }
    } while (!validCommand);
 
    return guestsList.findGuestPosition(fullName, email, phoneNumber);
  }
 
  private static void searchParticipantProtocol() {
    System.out.println("Se cauta toti invitatii" 
                       + " conform sirului de caractere introdus...");
    System.out.println("Introduceti sirul de caractere (fara spatii):");
    String expression = scanner.nextLine();
    ArrayList<Guest> matches = guestsList.searchByExpression(expression);
    for (int i=0; i<matches.size(); i++) {
      System.out.println(matches.get(i).toString());
    }
  }
 
  private static void updateParticipantProtocol() {
    System.out.println("Se actualizeaza detaliile unei persoane...");
    int position = searchGuestByCriteria();
 
    if (position < 0) {
      System.out.println("Eroare: Persoana nu este inscrisa la eveniment.");
      return;
    } 
 
    updateParticipantByField(guestsList.get(position));
  }
 
  private static void removeParticipantProtocol() {
    System.out.println("Se sterge o persoana existenta din lista...");
    int position = searchGuestByCriteria();
 
    if (position < 0) {
      System.out.println("Eroare: Persoana nu este inscrisa la eveniment.");
      return;
    } 
 
    guestsList.removeGuestByIndex(position);
    System.out.println("Stergerea persoanei s-a realizat cu succes.");
  }
 
  private static void findParticipantProtocol() {
    System.out.println("Se verifica daca o persoana este inscrisa...");
    int position = searchGuestByCriteria();
    System.out.println("Persoana "+ ((position < 0) ? "nu" : "") 
                       + " este inscrisa la eveniment.");
  }
 
  private static void addParticipantProtocol() {
    System.out.println("Se adauga o noua persoana...");
    String[] numeComplet = {getLastName(), getFirstName()};
    String email = getEmail();
    String telefon = getPhoneNumber();
    Guest newGuest = new Guest(numeComplet[0], numeComplet[1], email, telefon);
    int ret = guestsList.add(newGuest);
    if (ret < 0) {
      System.out.println("Eroare: persoana este deja inscrisa la eveniment.");
    }
  }
 
  private static void printCommandsList() {
    System.out.println("help         - Afiseaza aceasta lista de comenzi");
    System.out.println("add          - Adauga o noua persoana (inscriere)");
    System.out.println("check        - Verifica daca o persoana este inscrisa"
                       + " la eveniment");
    System.out.println("remove       - Sterge o persoana existenta din lista");
    System.out.println("update       - Actualizeaza detaliile unei persoane");
    System.out.println("guests       - Lista de persoane care participa"
                       + " la eveniment");
    System.out.println("waitlist     - Persoanele din lista de asteptare");
    System.out.println("available    - Numarul de locuri libere");
    System.out.println("guests_no    - Numarul de persoane care participa"
                       + " la eveniment");
    System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
    System.out.println("subscribe_no - Numarul total de persoane inscrise");
    System.out.println("search       - Cauta toti invitatii conform"
                       + " sirului de caractere introdus");
    System.out.println("quit         - Inchide aplicatia");
  }
 
  public static void main(String[] args) {
    System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
    guestsList = new GuestsList(scanner.nextInt());
    scanner.nextLine(); // ignore the new line
 
    boolean quit = false;
    while(!quit) {
      System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
      String command = scanner.nextLine();
      switch(command) {
        case "help":
          printCommandsList();
          break;
        case "add":
          addParticipantProtocol();
          break;
        case "check":
          findParticipantProtocol();
          break;
        case "remove":
          removeParticipantProtocol();
          break;
        case "update":
          updateParticipantProtocol();
          break;
        case "guests":
          guestsList.printParticipants();
          break;
        case "waitlist":
          guestsList.printWaitlist();
          break;
        case "available":
          System.out.println("Numarul de locuri ramase: "
                             + guestsList.availableNoSeats());
          break;
        case "guests_no":
          System.out.println("Numarul de participanti: "
                             + guestsList.participantsNo());
          break;
        case "waitlist_no":
          System.out.println("Dimensiunea listei de asteptare: "
                             + guestsList.waitlistSize());
          break;
        case "subscribe_no":
          System.out.println("Numarul total de persoane: "
                             + guestsList.interestedNo());
          break;
        case "search":
          searchParticipantProtocol();
          break;
        case "quit":
          System.out.println("Aplicatia se inchide...");
          quit = true;
          break;
        default:
          System.out.println("Comanda introdusa nu este valida.");
          System.out.println("Incercati inca o data.");
          break;
      }
    }
  }
}
