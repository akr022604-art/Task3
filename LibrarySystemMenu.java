import java.util.*;

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        String status = isIssued ? "Issued" : "Available";
        return "[" + id + "] " + title + " by " + author + " - " + status;
    }
}

// User class
class User {
    int id;
    String name;
    List<Book> issuedBooks;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    void issueBook(Book book) {
        issuedBooks.add(book);
    }

    void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User " + id + ": " + name + " | Issued Books: " + issuedBooks.size();
    }
}

// Library class
class Library {
    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    void addBook(Book book) {
        books.put(book.id, book);
    }

    void addUser(User user) {
        users.put(user.id, user);
    }

    void issueBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("❌ Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("❌ User not found!");
            return;
        }
        if (book.isIssued) {
            System.out.println("❌ Book already issued!");
            return;
        }

        book.isIssued = true;
        user.issueBook(book);
        System.out.println("✅ Book '" + book.title + "' issued to " + user.name);
    }

    void returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            System.out.println("❌ Book/User not found!");
            return;
        }
        if (!user.issuedBooks.contains(book)) {
            System.out.println("❌ This user did not issue this book!");
            return;
        }

        book.isIssued = false;
        user.returnBook(book);
        System.out.println("✅ Book '" + book.title + "' returned by " + user.name);
    }

    void showBooks() {
        System.out.println("\n📚 List of Books:");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    void showUsers() {
        System.out.println("\n👤 List of Users:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }
}

// Main Class with Menu
public class LibrarySystemMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Add some default books and users
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addBook(new Book(3, "Clean Code", "Robert C. Martin"));
        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n===== 📖 LIBRARY MENU =====");
            System.out.println("1. Show all books");
            System.out.println("2. Show all users");
            System.out.println("3. Add book");
            System.out.println("4. Add user");
            System.out.println("5. Issue book");
            System.out.println("6. Return book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    library.showUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bid, title, author));
                    System.out.println("✅ Book added successfully!");
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String uname = sc.nextLine();
                    library.addUser(new User(uid, uname));
                    System.out.println("✅ User added successfully!");
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int issueBid = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUid = sc.nextInt();
                    library.issueBook(issueBid, issueUid);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    int returnBid = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUid = sc.nextInt();
                    library.returnBook(returnBid, returnUid);
                    break;
                case 7:
                    System.out.println("👋 Exiting Library System. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }
    }
}

