//Thanyanit Jongjitragan 6188075

/*  The structure of Triangle class is similar to Rectangle */
public class Triangle extends Shape {
	
	   // Private member variables
	   private double base,height;
	
	   // Constructors
	   public Triangle() {
		   super();
	   }
	   public Triangle(String color, double base, double height) {
	      super(color);
	      this.base = base;
	      this.height = height;
	   }

	   @Override
	   public String toString() {
		   return "Triangle[base="+base +", height="+height+ ",Shape[color=" + super.getColor() + "]";
	   }

	   // Override the inherited getArea() to provide the proper implementation
	   @Override
	   public double getArea() {
		   return 0.5*base*height;
	   }

	   public double getArea(double base, double height) {
		   this.base=base;
		   this.height=height;
		   return 0.5*base*height;
	   }

}
