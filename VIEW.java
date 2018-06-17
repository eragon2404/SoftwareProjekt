import ea.*;
import java.util.LinkedList;

public class VIEW extends Game 
{
    private CONTROLLER c;
    public MODEL m;
    public LinkedList <OBJECT>observables;
    public int sx;
    public int sy;

    public VIEW(int aBahn, int x, int y) {
        super(x, y);
        m = new MODEL(this,aBahn,x,y);
        c = new CONTROLLER(m,this); 
        observables = new LinkedList();   
        observables.add(m.getSpieler());
        sx = x;
        sy = y;
    }
    
    public void hinzufuegen(Raum obj)
    {
        wurzel.add(obj);
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