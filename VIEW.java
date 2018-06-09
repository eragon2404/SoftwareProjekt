import ea.*;
import java.util.LinkedList;

public class VIEW extends Game 
{
    private CONTROLLER c;
    private MODEL m;
    public LinkedList <OBJECT>observables;

    public VIEW(int aBahn, int x, int y) {
        super(x, y);
        m = new MODEL(this,aBahn,x,y);
        c = new CONTROLLER(m,this); 
        observables = new LinkedList();      
    }
    
    public void hinzufuegen(Raum obj)
    {
        wurzel.add(obj);
    }

    @Override
    public void tasteReagieren(int code) {
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
                obj.getTexture().positionSetzen(obj.getPosX(),obj.getPosY());
                obj.actionPerformed();
                System.out.println("Changed");
            }
        }
    }
}