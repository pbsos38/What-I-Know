package Sorts;

public class x extends Thread{

    public static void main( String[] args){

    x t = new x(); Thread x1= new Thread( t );

x1.start();

}

    public void run(){

for( int i = 0; i < 3; ++i)

        {



        System.out.print( i + "..");



        }}}