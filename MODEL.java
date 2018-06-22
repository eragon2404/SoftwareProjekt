import ea.*;
public class MODEL
{
    public SPIELER spieler;
    public BAHN[] bahnen;
    public HINDERNISS[] hindernisse;
    public int x;
    public int y;
    
    public MODEL(int aBahn,int nx,int ny)
    {
        bahnen = new BAHN[aBahn];
        x = nx;
        y = ny;
        for(int i = 0; i < aBahn; i++)
        {
            bahnen[i] = new BAHN(i+1,aBahn,x,y);
        }
        hindernisse = new HINDERNISS[1];
        hindernisse[0] = new Fels(100,100);
    }
    
    public void tick()
    {
        for(int i = 0; i < bahnen.length; i++)
        {
            bahnen[i].check(spieler);
        }
    }
    
    public SPIELER getSpieler()
    {
        return spieler;
    }
    
    public int getBreite()
    {
        return x/(bahnen.length+2);
    }
    
    public int getAbahnen()
    {
        return bahnen.length;
    }
    
    public int getLenHindernisse()
    {
        return hindernisse.length;
    }
    
    public HINDERNISS getHinderniss(int i)
    {
        return hindernisse[i];
    }
    
    public void addHind(int bahn, HINDERNISS hind)
    {
        bahnen[bahn].addOBJ(hind);
    }
    
    public void newSpieler(SPIELER newSp)
    {
        spieler = newSp;
    }
        
}
    