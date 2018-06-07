import ea.*;
public class MODEL
{
    private VIEW v;
    public SPIELER spieler;
    public MODEL(VIEW newv)
    {
        v = newv;
        spieler = new Oktopus();
        v.hinzufügen(spieler.textur);
    }
    
    public void up()
    {
        spieler.links();
    }
}
    