import ea.*;
public class MODEL
{
    private VIEW v;
    public SPIELER spieler;
    public BAHN[] bahnen;
    public MODEL(VIEW newv,int aBahn,int x,int y)
    {
        v = newv;
        bahnen = new BAHN[aBahn];
        for(int i = 0; i < aBahn; i++)
        {
            bahnen[i] = new BAHN(i+1,aBahn,x,y);
        }
        spieler = new Oktopus();
        v.hinzufuegen(spieler.textur);
        v.addObservable(spieler);
    }
    
    public void tick()
    {
        for(int i = 0; i < bahnen.length; i++)
        {
        }
    }
}
    