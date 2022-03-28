package nl.cubicated.calclambda;

public interface CalcWithStartValue {
	// interface voor berekenen calc met int en doubles
	double applyWithStartValue(int startValue,int a, int b);
	double applyWithStartValuedbl(double startValue,double a, double b);
	double sumWithStartValue(int startValue,int []a);
	double sumWithStartValuedbl(double startValue,double []a);
}
