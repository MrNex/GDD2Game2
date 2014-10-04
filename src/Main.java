import mathematics.Vec;

/**
 * Defines the entry point for the program
 * @author Nex
 *
 */
public class Main {

	/**
	 * Entry point for program
	 * @param args Expecting no args. Compiled through eclipse.
	 */
	public static void main(String[] args) {
		
		//The below code is just testing the vector (Vec class) in the mathematics library.
		//Create
		Vec myVector;
		//Test component construction
		myVector = new Vec(5, 0, 0);
		
		//Test copy construction
		Vec myOtherVector;
		myOtherVector = new Vec(myVector);
		
		//Swap components
		myOtherVector.setComponent(2, myVector.getComponent(0));
		myOtherVector.setComponent(0, 0);
		
		//Test addition
		Vec sum = Vec.add(myVector, myOtherVector);
		
		System.out.printf("myVector: %s\nmyOtherVector: %s\nsum: %s\n", myVector.toString(), myOtherVector.toString(), sum.toString());
		
		//Test dot products
		double dotProd = sum.dot(myVector);
		double otherDotProd = sum.dot(myOtherVector);
		
		System.out.printf("myVector dot myOtherVector: %f\nsum dot myVector: %f\nsum dot myOtherVector: %f\n",myVector.dot(myOtherVector), dotProd, otherDotProd);
	}

}
