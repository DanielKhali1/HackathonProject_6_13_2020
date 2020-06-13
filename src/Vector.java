
public class Vector 
{
	public double x;
	public double y;
	
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	void addVector(Vector vect)
	{
		x += vect.x;
		y += vect.y;
	}
	
	void subVector(Vector vect)
	{
		x -= vect.x;
		y -= vect.y;
	}
	
	void divVector(double constant)
	{
		x /= constant; 
		y /= constant;
	}
	
	void multVector(double constant)
	{
		x *= constant; 
		y *= constant;
	}
	
	double returnMag()
	{
		double mag = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
		return mag;
	}
	
	void normalize()
	{
		divVector(returnMag());
	}
	
}
