import ea.*;
import java.util.*;

public class VIEW extends Game 
{
    private CONTROLLER c;
    private MODEL m;
    private LinkedList obsearvables;

    public VIEW() {
        super(1000, 1000);
        m = new MODEL(this);
        c = new CONTROLLER(m,this); 
    }
    
    public void hinzufuegen(Raum obj)
    {
        wurzel.add(obj);
    }

    @Override
    public void tasteReagieren(int code) {
        c.taste(code);
    }
}