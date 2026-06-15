package LibraryBookIssueSystem;

public class Main{
    public static void main(String[] args) {
        LibraryBook l = new LibraryBook("ee009","strategy",2,0,0);
        try{
            l.issueBook();
        }catch(BookNotAvailableException e){
            System.out.println("Error: " + e.getMessage());
        }
        try{
            l.returnBook();
        }catch(InvalidReturnException e){
            System.out.println("Error: " + e.getMessage());
        }

            l.displayStatus();
    }
}
