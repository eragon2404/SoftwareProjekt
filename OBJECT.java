import ea.*;
public abstract class OBJECT
{
    public boolean setChanged;
    public Raum Texture;
    public float PosX;
    public float PosY;
    public int breite;
    
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
    
        public int getBreite()
    {
        return breite;
    }
}
