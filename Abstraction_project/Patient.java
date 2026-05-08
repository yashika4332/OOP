package Abstraction_project;

public class Patient extends Person{
    private String disease;
    private int roomNo;

    Patient(String name,int age,int Id,String disease,int roomNo){
        super(name,age,Id);
        this.disease = disease;
        this.roomNo = roomNo;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getDisease() {
        return disease;
    }

    public int getRoomNo() {
        return roomNo;
    }

    void displayInfo(){
        System.out.println(getName());
        System.out.println(getAge());
        System.out.println(getId());
        System.out.println(getDisease());
        System.out.println(getRoomNo());
    }

    void getReport(){
        System.out.println("generating report");
    }
}
