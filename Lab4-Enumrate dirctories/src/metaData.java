import java.io.File;
import java.io.Serializable;

public class metaData implements Serializable {

   private String filename; 
   private Boolean readable;
   private Boolean writable;
   private Boolean executable;
   private long lastmodified;
   private String path; 


    public metaData(File file){
        this.filename = file.getName();
        this.readable = file.canRead();
        this.writable = file.canWrite();
        this.executable = file.canExecute();
        this.lastmodified = file.lastModified();
        this.path = file.getAbsolutePath();
    }

    public String toString(){
        
        return "\n-----------------------"
        + "\n name : "+ filename
        + "\n Readable: "+ readable
        + "\n Writable: "+ writable
        + "\n Executable: "+ executable
        + "\n Last Modified: "+ lastmodified
        + "\n Path: "+ path;
    }

} 
