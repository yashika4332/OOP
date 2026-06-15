package LibraryBookIssueSystem;

import java.util.Scanner;

abstract public class Book implements LibraryActions{
    private String bookId;
    private String title;
    private int totalCopies;
    private int availableCopies;
    private int issuedCopies;

    Book(String bookId,String title,int totalCopies, int availableCopies,int issuedCopies){
        this.bookId = bookId;
        this.title = title;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.issuedCopies = 0;
    }

    boolean checkActivity(){
        return availableCopies > 0;
    }

    String getTitle() { return title; }
    int getAvailableCopies() {
        return availableCopies;
    }
    int getIssuedCopies() {
        return issuedCopies;
    }
    void setAvailableCopies(int n) {
        availableCopies = n;
    }
    void setIssuedCopies(int n) {
        issuedCopies = n;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public abstract void issueBook() throws BookNotAvailableException;
    public abstract void returnBook() throws InvalidReturnException;
    public abstract void displayStatus();









}
