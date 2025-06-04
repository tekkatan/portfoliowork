# Welcome

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 

# BinaryCalc Project

### Usage
- Binary functions for common mathematical use
- This class does not use the ++ or -- arithmetic operators
- Why using this way? 
- Well because it can be used in all OOP languages
      And it explains the algorithm of Binary thinking in
      an usefull way.
- Using this in multithreaded programs is discouraged

**Example**
- Make sure to include the BinaryFunctions class in your project appropiate folder

- The following testcases examples are written using
junit5 via mvn. See the [pom file](https://github.com/tekkatan/portfoliowork/tree/main/javaprojects/binarycalc "goto map with pom file") for more info.

All test cases are tested on:
1. Usage of method in general
2. Usage of method with zero values and that
   it handles this correctly.
3. Note the `BeforeEach` to setup in the testfile. Do not delete this otherwise the source class won't be initialized before testing it.

**Source: Calculating SQRT in a Binary way**
```java
public int sqrt(int n){
        // only non-negative input
        // Binary numeral system base 2
        int x=n;
        int c=0;
        // int is32bits, d starts at the highest power of 4
        // d=1<<30 will shift the second to top bit,
        // same as writing (maxInt)+1/2 = 1073741824
        try{
            int d=1<<30; 
            //System.out.println("d=1<<30 outputs: "+d); //1073741824
            // as long as d is bigger than n
            while(d>n){
                d>>=2;
            }
            //System.out.println("while (d>n) d>>=2 output: "+d); //16
            while(d!=0){
                if(x>=increment(c,d)){
                    x=x-increment(c, d);
                    c=c>>1;
                    c=increment(c, d);
                }
                else{
                    c>>=1;
                }
                d>>=2;
            }
            //return c;
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
        
    }

```


**TestCase**
```java 
 BinaryFunctions bFunctions;

    @BeforeEach
    void setup(){
        bFunctions=new BinaryFunctions();
    }
    // Tests for SQRT method
    @Test
    @DisplayName("Simple example of getting sqrt")
    public void testSqrt()
    {
        assertEquals(5,bFunctions.sqrt(25),
            "Regular sqrt should work");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    void testSQRTWithZeroValue(){
        assertEquals(0,bFunctions.sqrt(0),
        "The sqrt of 0 should be 0");
    }

```
**Source: Calculating Modulo in a Binary way**
```java
 public int modulo(int x,int y){
        try{
            while(x>y){
                x=(x&y)+(x>>3);
            }
            return (x==y)?0:x;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return (x==y)?0:x;
    }

```
**TestCase**
```java
// Modulo
    @Test
    @DisplayName("Simple test of modulo")
    public void testModulo(){
        assertEquals(3,bFunctions.modulo(3, 7),
            "Regular 3%7");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testModuloWithZeroValue(){
        assertEquals(0,bFunctions.modulo(0, 0),
            "Modulo of 0 % 0");
        assertEquals(0,bFunctions.modulo(3, 0),
            "Modulo of 3 % 0");
        assertEquals(0,bFunctions.modulo(0, 3),
            "Modulo of 0 % 3");
    }
```

**Source: Calculating Increment in a Binary way**

```java
public int increment(int x, int y){
        try{
            while(y!=0){
                int  bucket=x&y;
                x=x^y;
                y=bucket<<1;
            }
            return x;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return x;
    }
```
**TestCase**
```java
// Increment
    @Test
    @DisplayName("Simple test of increment")
    public void testIncrement(){
        assertEquals(4,bFunctions.increment(3, 1),
            "Regular 3+1");
    }
    @RepeatedTest(2)
    @DisplayName("Ensure that it can handle zero correctly")
    public void testIncrementWithZeroValue(){
        assertEquals(0,bFunctions.increment(0, 0),
            "increment of 0 + 0");
        assertEquals(3,bFunctions.increment(3, 0),
            "increment of 3 + 0");
        assertEquals(3,bFunctions.increment(0, 3),
            "increment of 0 + 3");
    }
```