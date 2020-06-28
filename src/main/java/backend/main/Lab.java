package backend.main;


import backend.utils.EuroFormatter;

public class Lab {

    public static void main(String[] args){
        double x = 234.56789;
        System.out.println(EuroFormatter.formatEuro(x));
    }

}
