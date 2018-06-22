import ea.*;
import java.util.LinkedList;

public class VIEW extends Game 
{
    private CONTROLLER c;
    public MODEL m;
    public LinkedList <OBJECT>observables1;
    public LinkedList <OBJECT>observables0;
    public Knoten Hintergrund;
    public Knoten Mittelgrund;
    public Knoten Vordergrund;
    int sx;
    int sy;
    

    public VIEW(int aBahn, CONTROLLER newc) {
        super(1000,1000,"Flipinity");
        observables1 = new LinkedList();
        observables0 = new LinkedList();   
        Hintergrund = new Knoten();
        Mittelgrund = new Knoten();
        Vordergrund = new Knoten();
        wurzel.add(Hintergrund);
        wurzel.add(Mittelgrund);
        wurzel.add(Vordergrund);
        BoundingRechteck Fenster = fensterGroesse();
        sx = (int)Fenster.breite;
        sy = (int)Fenster.hoehe;
        c = newc; 
    }
    
    public void setModel(MODEL newM)
    {
        m = newM;
    }
    
    public int getSX()
    {
        return sx;
    }
    public int getSY()
    {
        return sy;
    }
    
    public void newHintergrund(Raum obj)
    {
        Hintergrund.add(obj);
    }
    
    public void newMittelgrund(Raum obj)
    {
        Mittelgrund.add(obj);
    }
    
    public void newVordergrund(Raum obj)
    {
        Vordergrund.add(obj);
    }
    
    public void delHintergrund(OBJECT obj)
    {
        Hintergrund.entfernen(obj.gettextur());
        observables0.remove(obj);
    }
    
    public void delMittelgrund(OBJECT obj)
    {
        Mittelgrund.entfernen(obj.gettextur());
        observables1.remove(obj);
    }
    
    public void delVordergrund(Raum obj)
    {
        Vordergrund.entfernen(obj);
    }
    
    public void clearHintergrund()
    {
        observables0.clear();
        Hintergrund.leeren();
    }
    
    public void clearMittelgrund()
    {
        observables1.clear();
        Mittelgrund.leeren();
    }

    @Override
    public void tasteReagieren(int code) 
    {
        c.taste(code);
    }
    
    public void addObservable0(OBJECT obj)
    {
        observables0.add(obj);
    }
    
    public void addObservable(OBJECT obj)
    {
        observables1.add(obj);
    }
    
    public void update()
    {
        for(int i = 0; i < observables1.size(); i++)
        {
            OBJECT obj = observables1.get(i);
            if(obj.setChanged == true)
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());
                obj.actionPerformed();
                System.out.println("Changed");
            }
            if(obj.getPosY() > sy)
            {
                delMittelgrund(obj);
            }
        }
        
        for(int i = 0; i < observables0.size(); i++)
        {
            OBJECT obj = observables0.get(i);
            if(obj.setChanged == true)
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());
                obj.actionPerformed();
                System.out.println("Changed");
            }
        }
    }
    
    public void terminate()
    {
        this.schliessen();
    }
}