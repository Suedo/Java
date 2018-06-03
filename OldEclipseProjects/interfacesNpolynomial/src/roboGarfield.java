interface pet {
    void sleep();
}
interface robot {
    void sleep();
}

public class roboGarfield {

    private final pet myPetIdentity = new pet() {
      public void sleep() { System.out.println("sleeping as pet"); }
      public String toString() { return roboGarfield.this.toString(); };
    };
    private final robot myRobotIdentity = new robot() {
      public void sleep() { System.out.println("recharging as robot"); }
      public String toString() { return roboGarfield.this.toString(); };
    };

    public final pet asPet() {
      return myPetIdentity;
    }
    public final robot asRobot() {
      return myRobotIdentity;
    }
    public void dbt(){
    	System.out.println("dbt : " + myPetIdentity);
    }
    public static void main(String[] args){
        roboGarfield t = new roboGarfield();
        t.asPet().sleep(); 
        t.asRobot().sleep();
        t.dbt();
    }    
}