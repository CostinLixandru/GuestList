package v1;

public class Guest {
	  private String nume;
	  private String prenume;
	  private String email;
	  private String telefon;
	 
	  public Guest(String nume, String prenume, String email, String telefon) {
	    this.nume = nume;
	    this.prenume = prenume;
	    this.email = email;
	    this.telefon = telefon;
	  }
	 
	  public boolean isEqual(Guest g) {
	    return email.equalsIgnoreCase(g.email);
	  }
	 
	  public boolean isEqualByName(String nume, String prenume) {
	    return nume.equalsIgnoreCase(this.nume) && 
	            prenume.equalsIgnoreCase(this.prenume);
	  }
	 
	  public boolean isEqualByEmail(String email) {
	    return this.email.equalsIgnoreCase(email);
	  }
	 
	  public boolean isEqualByPhone(String telefon) {
	    return this.telefon.equalsIgnoreCase(telefon);
	  }
	 
	  public boolean updateField(String nume, String prenume,            //update the Guest by field
	                             String email, String telefon) {
	    if (nume != null) {
	      this.nume = nume;
	      return true;
	    }
	    if (prenume != null) {
	      this.prenume = prenume;
	      return true;
	    }
	    if (email != null) {
	      this.email = email;
	      return true;
	    }
	    if (telefon != null) {
	      this.telefon = telefon;
	      return true;
	    }
	 
	    System.out.println("Error in updateField(...)");
	    return false;
	  }
	 
	  public boolean smartSearch(String expression) {                    // search for expression in the guest data
		  expression = expression.toLowerCase();
	    return this.nume.toLowerCase().contains(expression) ||
	        this.prenume.toLowerCase().contains(expression) ||
	        this.email.toLowerCase().contains(expression)   ||
	        this.telefon.toLowerCase().contains(expression);
	  }
	 @Override
	  public String toString() {
	    return "Nume: " + this.nume + " " + this.prenume + ", Email: "+ this.email + ", Telefon: " + this.telefon; 
	  }
	 
	  public void notifyParticipation() {
	    System.out.println("Ai fost inscris pe numele " + this.nume + " si prenumele " + this.prenume + ".");
	  }
	 
	  public void notifyWaitingList(int orderNo) {
	    System.out.println( this.nume + " " + this.prenume + "nu exista loc pe lista de invitati si ai fost adaugat pe lista de asteptare cu numarul de ordine " + orderNo
	    		+ ". Vei fi notificat cand se va elibera un loc");
	  }
}
