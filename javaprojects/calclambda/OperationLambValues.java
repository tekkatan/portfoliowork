package nl.cubicated.calclambda;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class OperationLambValues {
	
	// uitgeschreven tests
	// voor alle enum methods met input integer en input double(1 decimal)
	public double calculate(int startValue,int a, int b,OperationLamb ol) {
		return ol.applyWithStartValue(startValue, a, b);
	}
	public double calculatedbl(double d,double e, double f,OperationLamb ol) {
		return ol.applyWithStartValuedbl(d, e, f);
	}
	public double calcsumArray(int startValue,int[]a,OperationLamb ols) {
		return ols.sumWithStartValue(startValue,a);
	}
	public double calcsumArraydbl(double startValue,double[]a,OperationLamb ols) {
		return ols.sumWithStartValuedbl(startValue,a);
	}
	// ADD testen
	// met nul als startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumNul() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(0, 3, 1, OperationLamb.valueOf("ADD"));
		assertEquals(4.0,result,4.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumNuldbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(0.0, 3.0, 1.0, OperationLamb.valueOf("ADD"));
		assertEquals(4.0,result,4.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnumNul() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,3,5};
		double result=op.calcsumArray(0, a, OperationLamb.valueOf("ADD"));
		assertEquals(10.0,result,10.0);
	}
	//array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumNularrdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(0.0, a, OperationLamb.valueOf("ADD"));
		assertEquals(10.0,result,10.0);
	}
	// met startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnum() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(10, 3, 1, OperationLamb.valueOf("ADD"));
		assertEquals(14.0,result,14.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumdbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(10.0, 3.0, 1.0, OperationLamb.valueOf("ADD"));
		assertEquals(14.0,result,14.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnum() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,3,5};
		double result=op.calcsumArray(10, a, OperationLamb.valueOf("ADD"));
		assertEquals(20.0,result,20.0);
	}
	// array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumarrdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(10.0, a, OperationLamb.valueOf("ADD"));
		assertEquals(20.0,result,20.0);
	}
	
	
	
	
	// MULTIPLY testen
	// met nul als startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumNulMul() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(0, 3, 1, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(3.0,result,3.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumNulMuldbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(0.0, 3.0, 1.0, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(3.0,result,3.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulMul() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,3,5};
		double result=op.calcsumArray(0, a, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(30.0,result,30.0);
	}
	//array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulMuldbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(0.0, a, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(30.0,result,30.0);
	}
	
	// met startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumMul() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(10, 2, 2, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(40.0,result,40.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumMuldbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(10.0, 2.0, 2.0, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(40.0,result,40.0);
	}
	// array
	@Test
	public void calcWhenSumUsingSelfMadeEnumMul() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,2,2};
		double result=op.calcsumArray(10, a, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(80.0,result,80.0);
	}
	// array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumMuldbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,2.0,2.0};
		double result=op.calcsumArraydbl(10.0, a, OperationLamb.valueOf("MULTIPLY"));
		assertEquals(80.0,result,80.0);
	}
	
	
	// SUBTRACT testen
	// met nul als startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumNulSub() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(0, 3, 1, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(2.0,result,2.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumNulSubdbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(0.0, 3.0, 1.0, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(2.0,result,2.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulSub() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,3,5};
		double result=op.calcsumArray(0, a, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(-10.0,result,-10.0);
	}
	//array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulSubdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(0.0, a, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(-10.0,result,-10.0);
	}
	// met startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumSub() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(10, 2, 2, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(6.0,result,6.0);
	}
	// doubles
	@Test
	public void calcWhenUsingSelfmadeEnumSubdbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(10.0, 2.0, 2.0, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(6.0,result,6.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnumSub() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,2,2};
		double result=op.calcsumArray(10, a, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(4.0,result,4.0);
	}
	// array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumSubdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,2.0,2.0};
		double result=op.calcsumArraydbl(10.0, a, OperationLamb.valueOf("SUBTRACT"));
		assertEquals(4.0,result,4.0);
	}
	
	// Divide testen
	// met nul als startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumNulDiv() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(0, 3, 1, OperationLamb.valueOf("DIVIDE"));
		assertEquals(3.0,result,3.0);
	}
	//double
	@Test
	public void calcWhenUsingSelfmadeEnumNulDivdbl() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculatedbl(0.0, 3.0, 1.0, OperationLamb.valueOf("DIVIDE"));
		assertEquals(3.0,result,3.0);
	}
	//array
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulDiv() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,3,5};
		double result=op.calcsumArray(0, a, OperationLamb.valueOf("DIVIDE"));
		assertEquals(0.13,result,0.13);
	}
	//array of doubles
	@Test
	public void calcWhenSumUsingSelfMadeEnumNulDivdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,3.0,5.0};
		double result=op.calcsumArraydbl(0.0, a, OperationLamb.valueOf("DIVIDE"));
		assertEquals(0.13,result,0.13);
	}
	// met startwaarde
	@Test
	public void calcWhenUsingSelfmadeEnumDiv() {
		OperationLambValues op=new OperationLambValues();
		double result=op.calculate(100, 2, 2, OperationLamb.valueOf("DIVIDE"));
		assertEquals(25.0,result,25.0);
	}
	// array
	@Test
	public void calcWhenSumUsingSelfMadeEnumDiv() {
		OperationLambValues op=new OperationLambValues();
		int []a= {2,2,2};
		double result=op.calcsumArray(100, a, OperationLamb.valueOf("DIVIDE"));
		assertEquals(12.5,result,12.5);
	}
	// array
	@Test
	public void calcWhenSumUsingSelfMadeEnumDivdbl() {
		OperationLambValues op=new OperationLambValues();
		double []a= {2.0,2.0,2.0};
		double result=op.calcsumArraydbl(100.0, a, OperationLamb.valueOf("DIVIDE"));
		assertEquals(12.5,result,12.5);
	}

}
