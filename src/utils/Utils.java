package utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Utils {
    public static BitSet createBitSet(String encoded){
        BitSet bitSet = new BitSet(encoded.length());
        int bitcounter = 0;
        for(Character c : encoded.toCharArray()) {
            if(c.equals('1')) {
                bitSet.set(bitcounter);
            }
            bitcounter++;
        }
        return bitSet;
    }

    public static String decodeBitSet(BitSet set){
        StringBuilder binaryString = new StringBuilder();
        for(int i = 0; i <= set.length(); i++) {
            if(set.get(i)) {
                binaryString.append("1");
            } else {
                binaryString.append("0");
            }
        }
        return binaryString.toString();
    }

    public static BitSet createBitSet(List<Integer> list){
        int length = list.size();
        double logs = Math.log(length) / Math.log(2);
        int codeLen = (int) logs;
        codeLen++;

        StringBuilder res = new StringBuilder();
        String antet = intToCustomBinary(codeLen,9);
        res.append(antet);
        for(Integer n : list){
            String binary = intToCustomBinary(n, codeLen);
            res.append(binary);
        }

        return createBitSet(res.toString());

    }

    public static String intToCustomBinary(int n, int len){
        String binary = Integer.toBinaryString(n);
        StringBuilder res = new StringBuilder(binary);
        res = res.reverse();

        while( res.length() < len){
            res.append('0');
        }

        res = res.reverse();
        return res.toString();

    }

    public static List<Integer> LZWBinaryToIntegerList(BitSet bitSet){
        List<Integer> result = new ArrayList<>();
        String input = decodeBitSet(bitSet);
        StringBuilder sb = new StringBuilder(input);
        String antet = sb.substring(0,9).toString();
        sb = new StringBuilder(sb.substring(9));

        int codeLen = Integer.parseInt(antet,2);

        while(!sb.isEmpty()){
            String n = sb.substring(0,codeLen);
            sb = new StringBuilder(sb.substring(codeLen));
            result.add(Integer.parseInt(n,2));
        }

        return result;

    }


}
