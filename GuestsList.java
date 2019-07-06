package v1;

import java.util.ArrayList;

public class GuestsList {
 
  private int seatNo;

  private ArrayList<Guest> participants;
 
  public GuestsList(int seatNo) {
    this.seatNo = seatNo;
    this.participants = new ArrayList<Guest>(this.seatNo);
  }
 
  public int add(Guest guest) {                                      // add guest in GuestList of in waitingPositionList
    if (find(guest)) {          
      return -1;
    }
 
    this.participants.add(guest);                
    if (this.participants.size() <= this.seatNo) {         // notify that on guestList
      guest.notifyParticipation();
      return 0;
    }
 
    int waitlistPosition = this.participants.size() - this.seatNo;
    guest.notifyWaitingList(waitlistPosition);                 // notify that on waitinglist
    return waitlistPosition;
  }
 
  private boolean find(Guest guest) {                                 // check if guest allready exists
    for (int i=0; i<this.participants.size(); i++) {
      if (this.participants.get(i).isEqual(guest)) {
        return true;
      }
    }
    return false;
  }
 
  public int findGuestPosition(String[] fullName,                  // find guest by fullname, email, phoneNumber
                               String email, String phoneNumber) {
    if (fullName != null) {
      return findGuestByName(fullName);
    }
    if (email != null) {
      return findGuestByEmail(email);
    }
    if (phoneNumber != null) {
      return findGuestByPhone(phoneNumber);
    }
    System.out.println("Error in findGuestPosition" );
    return -2; 
  }
 
  public boolean removeGuestByIndex(int index) {                    // remove the guest at certain position
    if (index < 0 || index >= this.participants.size()) {
      System.out.println("Error: the index does not exist in the GuestList");
      return false;
    }
 
    if (index < this.seatNo && this.participants.size() > this.seatNo) {
      // notify the next person waiting
      participants.get(this.seatNo).notifyParticipation();
    }
    this.participants.remove(index);
 
    return true;
  }
 
  public Guest get(int index) {                                    // get the position of certain guest
    if (index < 0 || index >= this.participants.size()) {
      System.out.println("Error: the index does not exist in the GuestList");
      return null;
    }
 
    return participants.get(index);
  }
 
  public ArrayList<Guest> searchByExpression(String expression) {              // return an ArrayList with all guests that have the expression in one of their data
    ArrayList<Guest> matches = new ArrayList<Guest>();
    for (int i=0; i<this.participants.size(); i++) {
      if (this.participants.get(i).smartSearch(expression)) {
        matches.add(this.participants.get(i));
      }
    }
    return matches; 
  }
 
  public void printParticipants() {                                 // show all participants
    int total = this.participantsNo();
 
    if (total == 0) {
      System.out.println("Niciun participant inscris...");
      return;
    } 
 
    for (int i=0; i<total; i++) {
      System.out.println((i + 1) + ". " + this.participants.get(i).toString());
    }
  }
 
  public void printWaitlist() {                                // show all on waiting list
    int total = this.waitlistSize();
 
    if (total == 0) {
      System.out.println("Lista de asteptare este goala...");
      return;
    }
 
    for (int i=this.seatNo; i<this.participants.size(); i++) {
      System.out.println((i - this.seatNo + 1) + ". "
                         + this.participants.get(i).toString());
    }
  }
 
  public int availableNoSeats() {                               // available seats for guests
    int availableSeats = this.seatNo - this.participants.size(); 
    return availableSeats > 0 ? availableSeats : 0;
  }
 
  public int participantsNo() {                              // no of participants
    return Math.min(this.seatNo, this.participants.size()); 
  }
 
  public int waitlistSize() {                               // no on waitinglist
    int waitlistSize = this.participants.size() - this.seatNo;
    return waitlistSize > 0 ? waitlistSize : 0;
  }
 
  public int interestedNo() {                              // participants + on waiting list      
    return this.participants.size();
  }
 
  private int findGuestByName(String[] fullName) {        // find by fullname
    for (int i=0; i<this.participants.size(); i++) {
      if (this.participants.get(i).isEqualByName(fullName[0], fullName[1])) {
        return i;
      }
    }
    return -1;
  }
 
  private int findGuestByEmail(String email) {            // find by email
    for (int i=0; i<this.participants.size(); i++) {
      if (this.participants.get(i).isEqualByEmail(email)) {
        return i;
      }
    }
    return -1;
  }
 
  private int findGuestByPhone(String phoneNumber) {      // find by phoneNo
    for (int i=0; i<this.participants.size(); i++) { 
      if (this.participants.get(i).isEqualByPhone(phoneNumber)) {
        return i;
      }
    }
    return -1;
  }
}