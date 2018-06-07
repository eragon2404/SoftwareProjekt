import ea.*;
public abstract class SPIELER
{
    public Raum textur;
    public int breite;
    public int bahn;
    public int posX;
    public int posY;
    
    public void links()
    {
        bahn --;
        posX -= 20;
    }
    public void rechts()
    {
        bahn ++;
        posX += 20;
    }
    
    public int getbahn()
    {
        return bahn;
    }
}

    
