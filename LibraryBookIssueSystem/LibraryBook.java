package LibraryBookIssueSystem;

 class LibraryBook extends Book{
    LibraryBook(String bookId,String title,int totalCopies, int availableCopies,int issuedCopies){
        super(bookId, title, totalCopies,availableCopies, issuedCopies);
    }


  public  void issueBook() throws BookNotAvailableException{
if(!checkActivity()){
    throw new BookNotAvailableException( "No copies of \"" + getTitle() + "\" are available to issue.");
}
setAvailableCopies(getAvailableCopies() - 1);
        setIssuedCopies(getIssuedCopies() + 1);
        System.out.println("Book issued successfully: " + getTitle());
    }

    @Override
    public void returnBook() throws InvalidReturnException{
        if(getIssuedCopies() == 0){
            throw new InvalidReturnException("No copies of \"" + getTitle() + "\" are meant to return");
        }
        setAvailableCopies(getAvailableCopies() + 1);
        setIssuedCopies(getIssuedCopies()-1);
        System.out.println("Book returned successfully");

    }

    @Override
    public void displayStatus() {
        System.out.println( getTitle());
        System.out.println( getAvailableCopies());
        System.out.println(getIssuedCopies());
        System.out.println(getTotalCopies());
   }
 }

