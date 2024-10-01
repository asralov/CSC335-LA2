import java.util.ArrayList;
import java.util.Scanner;

public class commandProcessor {

    private Scanner sc;
    private Librarian librarian;

    public commandProcessor(Scanner scanner, Librarian libr) {
        this.sc = scanner;
        this.librarian = libr;
    }


    public ArrayList<Book> searchBook() {
		System.out.println("Choose an option for search command and enter one of the letters:");
		System.out.println("\"A\" - searching by Author;\n\"T\" - searching by Title;\n\"R\" - searching by Rating;");
		String option = sc.nextLine().toLowerCase();
		if (option.equals("a"))
		{
			System.out.print("Please enter an author's name for searching: ");
			String authorName = sc.nextLine();
			
			// getting the list of found books
			ArrayList<Book> books = librarian.searchByAuthor(authorName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Author): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}

				return booksToPrint;
				
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}		
		}
		else if (option.equals("t"))
		{
			System.out.println("Please enter the title of the book you are looking for: ");
			String titleName = sc.nextLine();
			// getting the list of found books
			ArrayList<Book> books = librarian.searchByTitle(titleName);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Title): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}
				return booksToPrint;
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}	
		}
		else if (option.equals("r"))
		{
			System.out.println("Please enter the rating of the book you are looking for: ");
			int rating = sc.nextInt();
            sc.nextLine();
			// getting the list of found books
			ArrayList<Book> books = librarian.searchByRating(rating);
			if (books.size() > 0)
			{
				// createa an arrayList and append each element to it
				ArrayList<Book> booksToPrint = new ArrayList<Book>();
				System.out.println("We found for you (by Rating): ");
				for (Book book:books)
				{
					System.out.println(book);
					booksToPrint.add(book);
				}
				return booksToPrint;
			}
			else
			{
				System.out.println("Oopsie, we do not have that book in our storage!");
			}	
    	}
		// return empty ArrayList
		return new ArrayList<Book>();
	}

	public void setToRead() {
		// prompts the user to enter the book to flag as read and calls
		// searchBook() function
		System.out.println("Please enter the book that you have read: \n");
		ArrayList<Book> booksFound = searchBook();
		// if none foudn return
		if (booksFound.size() == 0) return;

		int selectedBookIdx;
		// If there are multiple books found
		if (booksFound.size() > 1) {
			System.out.println("Please specify the book from the list: ");
			selectedBookIdx = sc.nextInt();
		} else {
			selectedBookIdx = 0;
		}
		librarian.setToRead(booksFound.get(selectedBookIdx));


		System.out.println("Book successfully set to read!");
	}

	public void rateBook() {

		System.out.println("Please enter the book to rate: \n");

		// THE FOLLOWING SECTION IS A COPY FROM setToRead()

		ArrayList<Book> booksFound = searchBook();
		// if none foudn return
		if (booksFound.size() == 0) return;
		int selectedBookIdx;
		// If there are multiple books found
		if (booksFound.size() > 1) {
			System.out.println("Please specify the book from the list: ");
			selectedBookIdx = sc.nextInt();
		} else {
			selectedBookIdx = 0;
		}

		System.out.println("Please enter a rating from 1 to 5: ");
		int rating  = sc.nextInt();
        sc.nextLine();

		librarian.rate(booksFound.get(selectedBookIdx), rating);

		System.out.println("Rating updated!");
	}

    public void addBook() {
        System.out.print("Please enter an author's name: ");
        String authorName = sc.nextLine();
        
        System.out.print("Please enter a title of the book: ");
        String titleDesc = sc.nextLine();
        
        System.out.print("Please enter a rating for the book: ");
        int rating = sc.nextInt();
        
        
        // To solve the issue with else block working
        sc.nextLine(); // Consume the leftover newline
        
        librarian.addBook(titleDesc, authorName, rating);
        System.out.println("Book added succesfully!");
        // for debugging purposes
        //System.out.print(librarian.books);
    }

    public void getBooks() {
		System.out.println("Choose an option for getBooks command and enter one of the letters:");
		System.out.println("\"A\" - get books by Author;" +
							"\n\"T\" - get books by Title;" +
							"\n\"R\" - get book that have been read;" +
							"\n\"U\" - get book that have not been read;");
		String option = sc.nextLine().toLowerCase();
	}

    public void suggestRead() {
        System.out.println("We highly recommend: " + librarian.suggestRead());
    }

    public void addBooks() {
        // TODO need to think of the way of finding 
        System.out.println("Enter a valid file name with .txt extension");
        System.out.println("(Note that a file should be located in the same directory)");
        String fileName = sc.nextLine();
        librarian.addBooks(fileName);
        System.out.println("Books added succesfully!");	
    }
    
}
