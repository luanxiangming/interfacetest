/**
 * Created by Oliver on 26/06/2017.
 */
public class StringBuffers {
    public static void StringBuffer_append() {
        StringBuffer buffer = new StringBuffer("Google");
        buffer.append(".com");
        System.out.println("StringBuffer 'Google' append '.com': " + buffer);
    }

    public static void StringBuffer_reverse() {
        StringBuffer buffer = new StringBuffer("apple");
        System.out.println("StringBuffer 'apple' reverse: " + buffer.reverse());
    }

    public static void StringBuffer_delete() {
        StringBuffer buffer = new StringBuffer("apple");
        System.out.println("StringBuffer 'apple' delete(2, 4): " + buffer.delete(2, 4));
    }

    public static void StringBuffer_insert() {
        StringBuffer buffer = new StringBuffer("apple");
        System.out.println("StringBuffer 'apple' insert(3, '-'): " + buffer.insert(3, '-'));
    }

    public static void StringBuffer_replace() {
        StringBuffer buffer = new StringBuffer("google");
        System.out.println("StringBuffer 'google' replace(1, 3, 'OO'): " + buffer.replace(1, 3, "OO"));
    }

    public static void main(String[] args) {
        StringBuffer_append();
        StringBuffer_reverse();
        StringBuffer_delete();
        StringBuffer_insert();
        StringBuffer_replace();
    }
}
