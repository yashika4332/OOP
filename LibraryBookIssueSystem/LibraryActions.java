package LibraryBookIssueSystem;

public interface LibraryActions {
    void issueBook() throws BookNotAvailableException;
    void returnBook() throws InvalidReturnException;
    void displayStatus();
}
