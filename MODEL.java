import ea.*;
public class MODEL
{
    public VIEW v;
    public SPIELER spieler;
    public BAHN[] bahnen;
    public int x;
    public int y;
    
    public MODEL(VIEW newv,int aBahn,int nx,int ny)
    {
        v = newv;
        bahnen = new BAHN[aBahn];
        x = nx;
        y = ny;
        for(int i = 0; i < aBahn; i++)
        {
            bahnen[i] = new BAHN(i+1,aBahn,x,y);
        }
        spieler = new Motorboot(this);
        v.hinzufuegen(spieler.textur);
    }
    
    public void tick()
    {
        for(int i = 0; i < bahnen.length; i++)
        {
        }
    }
    
    public SPIELER getSpieler()
    {
        return spieler;
    }
    
    public int getBreite()
    {
        return x/bahnen.length;
    }
}
    