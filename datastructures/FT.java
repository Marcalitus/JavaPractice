package net.datastructures;
public class FT {
double x; 
double y; 
double E = 1; 
public FT(double u, double v) {
x = u;
y = v;
};
public double IndExp() {
while (y != 0) {
double prevy = y;
double prevx = x;
double prevE = E;
if((y % 2) == 0) {
x = x*x ;
y = y / 2;
//Checking the preservation of critical information
if (prevy==0)
{ x=prevx; y=prevy;E=prevE;}
else if (y * 2 != prevy) {
x = prevx;
E = E* x;
y = prevy - 1;
}
} else {
E = E* x;
y = y - 1;
//Checking the preservation of critical information,
if (E != prevE * x) {

E = prevE * x;
}
if (y != (prevy - 1)) {
y = prevy - 1;
}}}
return E;
}
public void GoaUndExp() {
while (IndExp() != IndExp());
}
public void print() {
System.out.println(E);
}}
