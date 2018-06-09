import ea.*;
public abstract class OBJECT
{
    public boolean setChanged;
    private Raum Texture;
    public float PosX;
    public float PosY;
    
    public Raum getTexture()
    {
        return Texture;
    }
    
    public float getPosX()
    {
        return PosX;
    }
    
    public float getPosY()
    {
        return PosY;
    }
    
    public void tick(SPIELER spieler)
    {
    }
    
    public void actionPerformed()
    {
        setChanged = false;
    }
}
