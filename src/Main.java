import java.util.Scanner;

public class Main {

	static void outputOptions() {
		System.out.println("help - Afiseaza aceasta lista de comenzi");
		System.out.println("add - Adauga o noua persoana (inscriere)");
		System.out.println("check - Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove - Sterge o persoana existenta din lista");
		System.out.println("update - Actualizeaza detaliile unei persoane");
		System.out.println("guests - Lista de persoane care participa la eveniment");
		System.out.println("waitlist - Persoanele din lista de asteptare");
		System.out.println("available - Numarul de locuri libere");
		System.out.println("guests_no - Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no - Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no - Numarul total de persoane inscrise");
		System.out.println("search - Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("quit - Inchide aplciatia");
	}

	static Guest readDetails(int modAuth) {
		Scanner sc = new Scanner(System.in);
		Guest guest = new Guest();
		String input;
		if (modAuth == 0 || modAuth == 1) {
			System.out.println("Introduceti numele de familie:");
			input = sc.nextLine();
			guest.setLastName(input);
		}
		if (modAuth == 0 || modAuth == 1) {
			System.out.println("Introduceti prenumele:");
			input = sc.nextLine();
			guest.setFirstName(input);
		}
		if (modAuth == 0 || modAuth == 2) {
			System.out.println("Introduceti email:");
			input = sc.nextLine();
			while (!input.contains("@")) {
				System.out.println("Email invalid. Va rugam reintroduceti adresa de email:");
				input = sc.nextLine();
			}
			guest.setEmail(input);
		}
		if (modAuth == 0 || modAuth == 3) {
			System.out.println("Introduceti numar de telefon (format „+40733386463“):");
			input = sc.nextLine();
			guest.setPhoneNumber(input);
		}
		return guest;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
		GuestsList guestList = new GuestsList(sc.nextInt());
		Guest guest;
		int modAuth;
		System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
		String input = "quit";
		sc.nextLine();

		do {
			input = sc.nextLine();
			switch (input) {
			case "help":
				outputOptions();
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "add":
				System.out.println("Se adauga o noua persoana…");
				guest = readDetails(0);
				guestList.addGuest(guest);
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "check":
				System.out.println(
						"Alege modul de autentificare tastand: \n\"1\" - Numele si prenumele\n\"2\" - Email\n\"3\" - Numarul de telefon");
				modAuth = sc.nextInt();
				guest = readDetails(modAuth);
				if (guestList.isRegistered(guest, modAuth))
					System.out.println("Sunteti deja inscris!");
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "remove":
				System.out.println(
						"Alege modul de autentificare tastand: \n\"1\" - Numele si prenumele\n\"2\" - Email\n\"3\" - Numarul de telefon");
				modAuth = sc.nextInt();
				guest = readDetails(modAuth);
				if (guestList.removeGuest(guest, modAuth))
					System.out.println("Stergerea persoanei s-a realizat cu success.");
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "update":
				System.out.println(
						"Alege modul de autentificare tastand: \n\"1\" - Numele si prenumele\n\"2\" - Email\n\"3\" - Numarul de telefon");
				modAuth = sc.nextInt();
				guest = readDetails(modAuth);
				if (guestList.isRegistered(guest, modAuth)) {
					System.out.println(
							"Alege campul de actualizat, tastand: \n\"1\" - Numele si prenumele\n\"2\" - Email\n\"3\" - Numarul de telefon");
					modAuth = sc.nextInt();
					Guest newGuest = readDetails(modAuth);
					if (guestList.updateParticipant(guest, newGuest, modAuth))
						System.out.println("Update-ul s-a efectual cu succes!");
					else
						System.out.println("Informatiile nu au fost actualizate, va rugam reincercati.");
				}
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "guests":
				if (guestList.getGuestsNumber() > 0)
					guestList.guestsList();
				else
					System.out.println("Niciun participant inscris...");
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "waitlist":
				if (guestList.getWaitingGuestsNumber() > 0)
					guestList.waitingList();
				else
					System.out.println("Lista de asteptare este goala...");
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "available":
				System.out.println("Numarul de locuri libere este: " + guestList.getAvailableSeats());
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "guests_no":
				System.out.println("Numarul de participanti este: " + guestList.getGuestsNumber());
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "waitlist_no":
				System.out.println(
						"Numarul de persoane pe lista de asteptare este: " + guestList.getWaitingGuestsNumber());
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "subscribe_no":
				System.out.println("Numarul total de persoane inscrise este: " + guestList.getTotalGuests());
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "search":
				System.out.println("Introduceti caracterele pentru cautare: ");
				String stringSearch = sc.nextLine();
				guestList.outputPartialSearch(guestList.partialSearch(stringSearch));
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				break;
			default:
				System.out.println("Comanda introdusa nu este valida. Incercati inca o data.");
				break;
			}
		} while (!input.equals("quit"));
		sc.close();
	}

}
