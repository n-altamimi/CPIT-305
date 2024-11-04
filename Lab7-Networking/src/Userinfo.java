import java.io.Serializable;

public class Userinfo implements Serializable {
    private String name;
    private String BD; 
    private String gender;
    private String na;
    
    public Userinfo (String name, String bd, String gender,String nationality){
         this.name = name;
         this.BD = bd;
         this.gender = gender;
         this.na = nationality;
    }

    public String toString() {
        return "\n-----------------------"
        + "\n name : "+ name
        + "\n Birth of date : "+ BD
        + "\n Gender : "+ gender
        + "\n Nationality : "+ na;

    }
}
