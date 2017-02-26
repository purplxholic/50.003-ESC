package com.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Gt Zan on 24-Feb-17.
 */

public class FactorPrime {
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static List<BigInteger> prime_factorisation(BigInteger a){
        // impossible for values lower than 2
        if(a.compareTo(TWO) < 0){
            return null;
        }

        //6 110 & 001 = 000 ;011 , add 2 111
        //since 2 is a special number
        ArrayList<BigInteger> result = new ArrayList<BigInteger>();
        while(a.and(BigInteger.ONE).equals(BigInteger.ZERO)){ //AND THE BINARRY NUMBEWLFAWEFNA
            a = a.shiftRight(1);
            result.add(TWO);
        }

        //if can't be divdied by 2
        // generate and divide the number using odd numbers

        BigInteger b = BigInteger.valueOf(3); //starting with 3

        while(b.compareTo(a) < 0){ //while b is less than a
            if(b.isProbablePrime(10)){ //check whether b is a prime number

                BigInteger[] divideAndRemainder = a.divideAndRemainder(b); //returns [divided answer , mod answer]
                if(divideAndRemainder[1].equals(BigInteger.ZERO)){ //shows that a is divisible by b

                    result.add(b); //then b is a factor
                    a = divideAndRemainder[0]; //update a
                }
            }
            b = b.add(TWO);
        }
        result.add(b);

        return result;
    }
    //modified for cohort exercise 5 and 6
    public  static BigInteger[] division_threading(String string){
        BigInteger[] result = new BigInteger[2];
        BigInteger[] parsed = new BigInteger[3];
        String[] parsedString = string.split(" ");
        for (int i=0;i < parsedString.length;i++){
            parsed[i] = new BigInteger(parsedString[i]);
        }
        //2nd is the starting number, 3rd is the step to take
        BigInteger number = parsed[0];
        BigInteger divider = parsed[1];
        BigInteger step = parsed[2];
        boolean checker = true;
        while (checker){
            if (number.mod(divider).equals(0)){
                result[0] = divider;
                result[1] = number.divide(divider);
                checker = false;
            }
            else{
                divider.add(step);
            }
        }

        return result;
    }

    public static void main(String[] args){

        BigInteger first = new BigInteger("4294967297");
        BigInteger second = new BigInteger("1127451830576035879");
        BigInteger third = new BigInteger("160731047637009729259688920385507056726966793490579598495689711866432421212774967029895340327197901756096014299132623454583177072050452755510701340673282385647899694083881316194642417451570483466327782135730575564856185546487053034404560063433614723836456790266457438831626375556854133866958349817172727462462516466898479574402841071703909138062456567624565784254101568378407242273207660892036869708190688033351601539401621576507964841597205952722487750670904522932328731530640706457382162644738538813247139315456213401586618820517823576427094125197001270350087878270889717445401145792231674098948416888868250143592026973853973785120217077951766546939577520897245392186547279572494177680291506578508962707934879124914880885500726439625033021936728949277390185399024276547035995915648938170415663757378637207011391538009596833354107737156273037494727858302028663366296943925008647348769272035532265048049709827275179381252898675965528510619258376779171030556482884535728812916216625430187039533668677528079544176897647303445153643525354817413650848544778690688201005274443717680593899");


        System.out.println(prime_factorisation(first));

    }
}
