import ea.*;
public abstract class SPIELER extends OBJECT
{
    public Raum textur;
    public int breite;
    public int bahn;
    
    public void links()
    {
        bahn --;
        PosX -= 20;
        setChanged = true;
    }
    
    public void rechts()
    {
        bahn ++;
        PosX += 20;
        setChanged = true;
    }
    
    public int getbahn()
    {
        return bahn;
    }
    
    public Raum getTexture()
    {
        return textur;
    }
    
}

    
