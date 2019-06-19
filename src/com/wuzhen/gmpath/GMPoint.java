package com.wuzhen.gmpath;
public class GMPoint {
    public GMPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public GMPoint() 
    {
    }
    private double x = 0.0;

 
    private double y = 0.0;

   
 


    public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String ToFormatString(String splitChar)
    {
        return this.x + splitChar + this.y;
    }

    public static GMPoint FromStringBySpace(String shape)
    {
        return GMPoint.FromStringBySplitChar(shape, " ");
    }
    public static GMPoint FromStringByComma(String shape){
    	return GMPoint.FromStringBySplitChar(shape, ",");
    }
    public static GMPoint FromStringBySplitChar(String shape, String splitChar)
    {
        GMPoint xS = new GMPoint();
        if (shape != null)
        {
            String[] s = shape.split(splitChar);
            if (s.length == 2)
            {
                try
                {
                    xS.x = Double.valueOf(s[0].trim());
                    xS.y = Double.valueOf(s[1].trim());
                }
                catch(Exception ex)
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        } 
        return xS;
    }
    public static GMPoint FromStringByComma(String shape,char splitChar)
    {
        return GMPoint.FromStringBySplitChar(shape, ",");
    }
    public static double Distance(GMPoint bP, GMPoint eP)
    {
        return Math.sqrt(Math.pow(bP.x - eP.x,2) + Math.pow(bP.y-eP.y,2));
    }
}
