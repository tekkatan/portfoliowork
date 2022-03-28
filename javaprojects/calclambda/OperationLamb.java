package nl.cubicated.calclambda;

public enum OperationLamb implements CalcWithStartValue{
	//ADD, MULTIPLY, SUBTRACT,DIVIDE
	ADD{
		@Override
		public double applyWithStartValue(int startValue,int a,int b) {
			if(startValue==0) {
				// 0 geldt als default
				return a+b;
			}else {
				// startwaarde is geen 0
				return startValue+a+b;
			}
		}
		@Override
		public double applyWithStartValuedbl(double startValue,double a,double b) {
			if(startValue==0) {
				// 0 geldt als default
				return a+b;
			}else {
				// startwaarde is geen 0
				return startValue+a+b;
			}
		}

		@Override
		public double sumWithStartValue(int startValue,int[] a) {
			int sum=0;
			if(startValue==0) {
				for(int value :a) {
					sum+=value;
				}
				return sum;
			}else {
				for(int value :a) {
					sum+=value;
				}
				return sum+startValue;
			}
		}
		@Override
		public double sumWithStartValuedbl(double startValue,double[] a) {
			int sum=0;
			if(startValue==0) {
				for(double value :a) {
					sum+=value;
				}
				return sum;
			}else {
				for(double value :a) {
					sum+=value;
				}
				return sum+startValue;
			}
		}

	},
	MULTIPLY{
		@Override
		public double applyWithStartValue(int startValue,int a,int b) {
			if(startValue==0){
				return a*b;
			}else {
				startValue*=a;
				startValue*=b;
				return startValue;
			}
		}
		@Override
		public double applyWithStartValuedbl(double startValue,double a,double b) {
			if(startValue==0) {
				// 0 geldt als default
				return a*b;
			}else {
				// startwaarde is geen 0
				return startValue*a*b;
			}
		}
		@Override
		public double sumWithStartValue(int startValue,int[] a) {
			// factorial
			int sum=0;
			if(startValue==0) {
				for(int value :a) {
					sum*=value;
				}
				return sum+startValue;
			}else {
				for(int value :a) {
					startValue/=value;
				}
				return startValue;
			}
		}
		@Override
		public double sumWithStartValuedbl(double startValue,double[] a) {
			// factorial
			int sum=0;
			if(startValue==0) {
				for(double value :a) {
					sum*=value;
				}
				return sum+startValue;
			}else {
				for(double value :a) {
					startValue/=value;
				}
				return startValue;
			}
		}
	},
	SUBTRACT{
		@Override
		public double applyWithStartValue(int startValue,int a,int b) {
			if(startValue==0) {
				return a-b;
			}else {
				startValue-=a;
				startValue-=b;
				return startValue;
			}
			
		}
		@Override
		public double applyWithStartValuedbl(double startValue,double a,double b) {
			if(startValue==0) {
				// 0 geldt als default
				return a-b;
			}else {
				// startwaarde is geen 0
				return startValue-a-b;
			}
		}

		@Override
		public double sumWithStartValue(int startValue,int[] a) {
				for(int value :a) {
					startValue-=value;
				}
				return startValue;
		}
		@Override
		public double sumWithStartValuedbl(double startValue,double[] a) {
				for(double value :a) {
					startValue-=value;
				}
				return startValue;
		}
	},
	DIVIDE{
		@Override
		public double applyWithStartValue(int startValue,int a,int b) {
			if(startValue==0) {
				// 0 geldt als default
				// voorkomt een divide by 0
				return a/b;
			}else {
				// startwaarde is geen 0
				startValue/=a;
				startValue/=b;
				return startValue;
			}
		}
		@Override
		public double applyWithStartValuedbl(double startValue,double a,double b) {
			if(startValue==0) {
				// 0 geldt als default
				return a-b;
			}else {
				// startwaarde is geen 0
				startValue/=a;
				startValue/=b;
				return startValue;
			}
		}

		@Override
		public double sumWithStartValue(int startValue,int[] a) {
			// worteltrekken
			int sum=0;
			if(startValue==0) {
				for(int value :a) {
					sum/=value;
				}
				return sum;
			}else {
				sum=startValue/a[0];
				for(int value :a) {
					startValue/=value;
				}
				return startValue;
			}
		}
		@Override
		public double sumWithStartValuedbl(double startValue,double[] a) {
			// worteltrekken
			double sum=0;
			if(startValue==0) {
				for(double value :a) {
					sum/=value;
				}
				return sum;
			}else {
				sum=startValue/a[0];
				for(double value :a) {
					startValue/=value;
				}
				return startValue;
			}
		}
	};
		

	public abstract double applyWithStartValue(int startValue,int a, int b);
	public abstract double applyWithStartValuedbl(double d,double e, double f);
	public abstract double sumWithStartValue(int startValue,int[]a);
	public abstract double sumWithStartValuedbl(double startValue,double[]a);
}

