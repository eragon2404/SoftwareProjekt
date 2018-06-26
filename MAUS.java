import ea.*;
public class MAUS
{
   public Bild mausbild;
   public Punkt hotspot;
   public boolean absolut;
   public boolean bewegend;
   
  public MAUS()
  {    
      Bild mausbild = new Bild(0,0,"Recources/fadenkreuz.gif");
      Punkt hotspot = new Punkt(3,3);
      boolean absolut = false;
      boolean bewegend = false;
      //Maus maus = new Maus(mausbild, hotspot);
      System.out.println("HI");
      
  }
  
  public void klickReagierbarAnmelden(Klickreagierbar reagierer)
  {
  }
  
}
