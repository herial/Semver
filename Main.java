import java.util.regex.*; 
public class Main{

     public static void main(String[] args){
        System.out.println(determinePrecedence("1.1.0","2.0.0"));
        System.out.println(determinePrecedence("1.0.0","1.0.0-alpha"));
     }
     
     public static boolean determinePrecedence(String semantic1, String semantic2){
         
        Semantic sem1 = new Semantic(semantic1);
        Semantic sem2 = new Semantic(semantic2);
        
        if(sem1.major>sem2.major){
            return true;
        }else if(sem1.major==sem2.major){
             if(sem1.minor>sem2.minor){
                 return true;
             }else if(sem1.minor==sem2.minor){
                
                if(sem1.patch>sem2.patch){
                    return true;
                }else if(sem1.patch==sem2.patch){
                     if(sem1.preRelease!=null && sem2.preRelease==null){
                         return false;
                     }else{
                         return true;
                     }
                    
                }else{
                    return false;
                }
            
            }else{
                 return false;
            }
            
        }else{
            return false;
        }
        
         
         
         
     }
     
     
     
}

class Semantic{
    
    public final int major;
    
    public final int minor;
    
    public final int patch;
    
    public String preRelease;
    
    
    Semantic(String version){
    	Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(?:-([a-zA-Z0-9]+))?")
            .matcher(version);
    	if (!matcher.matches())
    	    throw new IllegalArgumentException("Invalid Semantic version");
        major = Integer.parseInt(matcher.group(1));
        minor = Integer.parseInt(matcher.group(2));
        patch = Integer.parseInt(matcher.group(3));
        preRelease = matcher.group(4);
    }
    
    
}
