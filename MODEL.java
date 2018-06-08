import ea.*;
public class MODEL
{
    private VIEW v;
    public SPIELER spieler;
    public MODEL(VIEW newv)
    {
        v = newv;
        spieler = new Baumstamm();
        v.hinzufuegen(spieler.textur);
    }
    
    public void up()
    {
        spieler.links();
    }
}
    