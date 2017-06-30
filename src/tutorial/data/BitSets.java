package data;

import java.util.BitSet;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * 一个BitSet类创建一种特殊类型的数组来保存位值。BitSet中数组大小会随需要增加。这和位向量（vector of bits）比较类似。
 */
public class BitSets {
    public static void main(String[] args) {
        BitSet bs1 = new java.util.BitSet(16);
        BitSet bs2 = new java.util.BitSet(16);

        for (int i = 1; i <= 16; i++) {
            if (i % 2 == 0) {
                bs1.set(i);
            } else if (i % 3 == 0) {
                bs2.set(i);
            }
        }
        System.out.println("BitSet1: " + bs1.toString());
        System.out.println("BitSet2: " + bs2.toString());

        bs2.and(bs1);
        System.out.println("BitSet2.and(BitSet1): " + bs2.toString());

        bs2.or(bs1);
        System.out.println("BitSet2.or(BitSet1): " + bs2.toString());

        bs2.xor(bs1);
        System.out.println("BitSet2.xor(BitSet1): " + bs2.toString());

    }
}
