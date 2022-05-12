import java.util.ArrayList;

public class GuestsList {
	// va tine evidenta inscrierilor la un eveniment

	private int maxSeats; // nr de locuri disponibile la eveniment (i.e. numar oferit la initializare)
	private ArrayList<Guest> participantsList = new ArrayList<Guest>(); // lista de participanti
	private ArrayList<Guest> waitingList = new ArrayList<Guest>(); // persoane aflate in asteptare

	public GuestsList(int maxSeats) {
		this.maxSeats = maxSeats;
		this.participantsList.ensureCapacity(maxSeats);
		this.waitingList.ensureCapacity(maxSeats);
	}

	// 7.obtinerea numarului de locuri disponibile
	int getAvailableSeats() {
		return this.maxSeats - participantsList.size();
	}

	// 8.obtinerea numarului de persoane participante (i.e. aflate in lista de
	// participare)
	int getGuestsNumber() {
		return this.participantsList.size();
	}

	// 9.obtinerea numarului de persoane din lista de asteptare
	int getWaitingGuestsNumber() {
		return this.waitingList.size();
	}

	// 10.obtinerea numarului total de persoane
	int getTotalGuests() {
		return this.participantsList.size() + this.waitingList.size();
	}

	// CheckParticipant (pt pct. 1, 2, 3, 9)
	int checkParticipant(Guest guest, int modAuth) { // cauta persoana
		for (Guest currentGuest : participantsList) {
			if (guest.equals(currentGuest, modAuth)) {
				return -1;
			}
		}
		for (Guest currentGuest : waitingList) {
			if (guest.equals(currentGuest, modAuth)) {
				return waitingList.indexOf(currentGuest) + 1;
			}
		}
		return 0;
	}

	// 1. adaugarea unei noi persoane (inscrise)
	int addGuest(Guest guest) {
		int result = checkParticipant(guest, 2);
		if (result == 0) {
			if (participantsList.size() < maxSeats) {
				participantsList.add(guest);
				System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!.");
			} else if (waitingList.size() < maxSeats) {
				waitingList.add(guest);
				System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "
						+ waitingList.size() + ". Te vom notifica daca un loc devine disponibil.");
			}
		} else
			System.out.println("Sunteti deja inscris!");
		return result;
	}

	// 2. determina daca o persoana este inscrisa la eveniment (in oricare lista)
	boolean isRegistered(Guest guest, int modAuth) { // determina daca o persoana este inscrisa
		return checkParticipant(guest, modAuth) != 0;
	}

	
	//pt 3, 4
	int findPersonIndex(Guest guest, int modAuth) {    
		for (int i = 0; i < participantsList.size(); i++) {
			if (guest.equals(participantsList.get(i), modAuth)) {
				return i + 1;  //nr pozitiv

			}
		}
		for (int i = 0; i < waitingList.size(); i++) {
			if (guest.equals(waitingList.get(i), modAuth)) {
				return (i + 1) * -1; //nr negativ 
			}
		}
		return 0;
	}

	// 3. eliminarea unei persoane (inscrise)
	boolean removeGuest(Guest guest, int modAuth) {
		int result = checkParticipant(guest, modAuth); // va permite cautarea si respectiv eliminarea unui
														// participant dupa fiecare criteriu mai sus mentionat
		switch (result) {
		case -1:
			participantsList.remove(findPersonIndex(guest, modAuth) - 1);
			System.out.println(findPersonIndex(guest, modAuth));
			if (this.getWaitingGuestsNumber() > 0) {
				participantsList.add(waitingList.get(0));
				waitingList.remove(0);
			}
			return true;
		case 0:
			return false;
		default:
			waitingList.remove(result - 1);
			return true;
		}
	}

	// 4.actualizarea detaliilor unei persoane inscrise
	boolean updateParticipant(Guest guest, Guest newGuest, int modAuth) {  // findPersonIndex
		int index = findPersonIndex(guest, modAuth); 
		switch (modAuth) {
		case 1:
			if (index < 0) { //waiting list
				waitingList.get(index * -1 - 1).setFirstName(newGuest.getFirstName());  //-4 * -1 4 -1 3
				waitingList.get(index * -1 - 1).setLastName(newGuest.getLastName());
			} else {   //participant list
				participantsList.get(index - 1).setFirstName(newGuest.getFirstName());
				participantsList.get(index - 1).setLastName(newGuest.getLastName());
			}
			return true;
		case 2:
			if (index < 0) {
				waitingList.get(index * -1 - 1).setEmail(newGuest.getEmail());
			} else {
				participantsList.get(index - 1).setEmail(newGuest.getEmail()); 
			}
			return true;
		case 3:
			if (index < 0) {
				waitingList.get(index * -1 - 1).setPhoneNumber(newGuest.getPhoneNumber());
			} else {
				participantsList.get(index - 1).setPhoneNumber(newGuest.getPhoneNumber());
			}
			return true;
		}
		return false;
	}

	void updateGuest(Guest guest, int modAuth, String lastName, String firstName) {
		for (Guest currentGuest : participantsList) {
			if (guest.equals(currentGuest, modAuth)) {
				guest.setLastName(lastName);
				guest.setFirstName(firstName);
				return;
			}
		}
		for (Guest currentGuest : waitingList) {
			if (guest.equals(currentGuest, modAuth)) {
				guest.setLastName(lastName);
				guest.setFirstName(firstName);
			}
		}
	}

	void updateGuest(Guest guest, int modAuth, boolean detailToUpdate, String data) {
		for (Guest currentGuest : participantsList) {
			if (guest.equals(currentGuest, modAuth)) {
				if (detailToUpdate) {
					guest.setEmail(data);
				} else
					guest.setPhoneNumber(data);
				return;
			}
		}
		for (Guest currentGuest : waitingList) {
			if (guest.equals(currentGuest, modAuth)) {
				if (detailToUpdate) {
					guest.setEmail(data);
				} else
					guest.setPhoneNumber(data);
			}
		}
	}

	// 5.obtinerea listei de persoane care au loc la eveniment (i.e. lista de
	// participare)
	void guestsList() {
		outputList(participantsList);
	}

	// 6.obtinerea listei de persoane din lista de asteptare
	void waitingList() {
		outputList(waitingList);
	}

	// 11.cautare partiala, dupa un subsir de caractere.
	ArrayList<Guest> partialSearch(String stringSearch) {
		stringSearch = stringSearch.toLowerCase();
		ArrayList<Guest> searchRes = new ArrayList<Guest>();
		for (Guest guest : participantsList) {
			if (guest.getFirstName().toLowerCase().contains(stringSearch)
					|| guest.getLastName().toLowerCase().contains(stringSearch)
					|| guest.getEmail().toLowerCase().contains(stringSearch)
					|| guest.getPhoneNumber().toLowerCase().contains(stringSearch)) {
				searchRes.add(guest);
			}
		}
		for (Guest guestX : waitingList) {
			if (guestX.getFirstName().toLowerCase().contains(stringSearch)
					|| guestX.getLastName().toLowerCase().contains(stringSearch)
					|| guestX.getEmail().toLowerCase().contains(stringSearch)
					|| guestX.getPhoneNumber().toLowerCase().contains(stringSearch)) {
				searchRes.add(guestX);
			}
		}
		return searchRes;
	}

	void outputPartialSearch(ArrayList<Guest> searchRes) {
		if (searchRes.size() == 0) {
			System.out.println("0 rezultate");
		}
		outputList(searchRes);
	}

	// OutputList
	void outputList(ArrayList<Guest> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". Nume:" + list.get(i).firstName + " " + list.get(i).lastName + ", Email: "
					+ list.get(i).email + ", Telefon: " + list.get(i).phoneNumber);
		}
	}
}
