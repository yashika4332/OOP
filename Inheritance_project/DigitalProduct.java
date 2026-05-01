package Inheritance_project;

public class DigitalProduct extends Product{
    private double fileSize;
    private String downloadLink;

    DigitalProduct(String Name,double Price,double fileSize, String downloadLink){
        super(Name,Price);
        this.fileSize = fileSize;
        this.downloadLink = downloadLink;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }
    void download(){
        System.out.println("Downloading: "+downloadLink);
    }
    void displayInfo(){
        super.displayInfo();
        System.out.println(getFileSize());
        System.out.println(getDownloadLink());
    }

}
