import ea.*;

public class VIEW extends Game 
{
    private CONTROLLER c;
    private MODEL m;

    public VIEW() {
        super(1000, 1000);
        m = new MODEL(this);
        c = new CONTROLLER(m,this);
    }
    
    public void hinzufügen(Raum obj)
    {
        wurzel.add(obj);
    }

    @Override
    public void tasteReagieren(int code) {
        c.taste(code);
    }
}