# Welcome

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 


# CalcLambda

### Usage
- This Project uses an
Interface > enum > abstract class > executing class structure.
- The executing class is
OperationLambValues. This class contains junit testcases.
- All test cases are tested for:
 1. double types
 2. arrays of ints
 3. arrays of doubles
 4. values starting from zero
 5. values starting from a different numberic value

**Example**: 
adding two numbers

```java
// Using zero as startvalue
// Add two numbers
@Test
	public void calcWhenUsingSelfmadeEnumNul() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(0, 3, 1, OperationLamb.valueOf("ADD"));
		assertEquals(4.0,result,4.0);
	}

```
**Example 2**: 
Subtract two numbers

```java
// Using zero as startvalue
// Subtract an array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulSubdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(0.0, a, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(-10.0,result,-10.0);
	}

```
