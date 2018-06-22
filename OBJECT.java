import ea.*;
public abstract class OBJECT
{
    public boolean setChanged;
    public Figur textur;
    public float PosX;
    public float PosY;
    public int breite;
    
    public Figur gettextur()
    {
        return textur;
    }
    
    public float getPosX()
    {
        return PosX;
    }
    
    public float getPosY()
    {
        return PosY;
    }
    
    public void tick()
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
