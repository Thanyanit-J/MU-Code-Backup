//Thanyanit Jongjitragan 6188075

/*  The structure of Circle class is similar to Rectangle */
public class Circle extends Shape {
	   // Private member variables
	   private double pi = 3.141592653589793238462643383279502884197;
	   private double radius;
	
	   // Constructors
	   public Circle() {
		   super();
	   }
	   public Circle(String color, double radius) {
	      super(color);
	      this.radius = radius;
	   }

	   @Override
	   public String toString() {
		   return "Circle[radius="+radius +",Shape[color=" + super.getColor() + "]";
	   }

	   // Override the inherited getArea() to provide the proper implementation
	   @Override
	   public double getArea() {
		   return pi*radius*radius;
	   }

	   public double getArea(double radius) {
		   this.radius=radius;
		   return pi*radius*radius;
	   }

}
