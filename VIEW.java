import ea.*;
import java.util.LinkedList;

public class VIEW extends Game 
{
    private CONTROLLER c;
    public MODEL m;
    public LinkedList <OBJECT>observables;
    public int sx;
    public int sy;
    public Knoten Hintergrund;
    public Knoten Mittelgrund;
    public Knoten Vordergrund;

    public VIEW(int aBahn, int x, int y) {
        super(x, y);
        observables = new LinkedList();   
        Hintergrund = new Knoten();
        Mittelgrund = new Knoten();
        Vordergrund = new Knoten();
        wurzel.add(Hintergrund);
        wurzel.add(Mittelgrund);
        wurzel.add(Vordergrund);
        sx = x;
        sy = y;
        m = new MODEL(this,aBahn,x,y);
        c = new CONTROLLER(m,this); 
        observables.add(m.getSpieler());
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

    @Override
    public void tasteReagieren(int code) 
    {
        c.taste(code);
    }
    
    public void addObservable(OBJECT obj)
    {
        observables.add(obj);
    }
    
    public void update()
    {
        for(int i = 0; i < observables.size(); i++)
        {
            OBJECT obj = observables.get(i);
            if(obj.setChanged == true)
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());
                obj.actionPerformed();
                System.out.println("Changed");
            }
            if(obj.getPosY() > sy)
            {
                observables.remove(obj);
                //wurzel.delete(obj);
            }
        }
    }
}