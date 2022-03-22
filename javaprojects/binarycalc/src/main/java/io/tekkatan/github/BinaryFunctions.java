package io.tekkatan.github;

public class BinaryFunctions{
    /*@Author= Tanja Bekker
    - Binary functions for common mathematical use
    - This class does not use the ++ or -- arithmetic operators
    - Why using this way? 
    - Well because it can be used in all OOP languages
      And it explains the algorithm of Binary thinking in
      an usefull way.
    - Using this in multithreaded programs is discouraged
    */
    
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
    public int division(int x, int y){
        int q=1;
        int n=1;
        int r=0;
        try{
            if((x>0&&y<0)||(x<0&&y>0))n=-1;
                // convert to positive
                // if either one is negative
                int tx=(x<0)?-x:x;
                int ty=(y<0)?-y:y;
                if(tx==ty){
                    r=0;
                    return 1*n;
                }else if(tx<ty){
                    if(x<0)
                        r=tx*n;
                    else
                        r=tx;
                    return 0;
                }
                while(ty<<1<=tx){
                    ty=ty<<1;
                    q=q<<1;
                }
                // the actual calling
                if(x<0)
                    q=q*n+division(-(tx-ty), y);
                else
                    q=q*n+division(tx-ty, y);
                return q;
        }catch(Exception e){
            e.printStackTrace();
        }
        return q;
    }
    

    public int multiplication(int x, int y){
        int r=0;
        int n=1;
        try{
            if((x>0&&y<0)||(x<0&&y>0))n=-1;
                // convert to positive
                // if either one is negative
                int tx=(x<0)?-x:x;
                int ty=(y<0)?-y:y;
                if(tx==ty){
                    r=0;
                    return 1*n;
                }else if(tx<ty){
                    if(x<0)
                        r=tx*n;
                    else
                        r=tx;
                    return 0;
            }else{
                while(y>0){
                    r+=((y&1)>0)?x:0;
                    x<<=1;
                    y>>=1;
                }
                return r;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return r;
        
        
    }
    public int power(int x, int y){
        int t=1;
        try{

           while(y!=0){
               t=t*x;
               y--;
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return t;
            
    }

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

    public int decrement(int x,int y){
        try{
            while(y!=0){
                int bucket=(~x)&y;
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

}