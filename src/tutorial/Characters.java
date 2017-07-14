/**
 * Created by Oliver on 26/06/2017.
 */
public class Characters {
    public static void Character_isLetter() {
        System.out.println("'C' isLetter: " + Character.isLetter('C'));
        System.out.println("'1' isLetter: " + Character.isLetter('1'));
    }

    public static void Character_isDigit() {
        System.out.println("'A' isDigit: " + Character.isDigit('A'));
        System.out.println("'5' isDigit: " + Character.isDigit('5'));
    }

    public static void Character_isWhitespace() {
        System.out.println("'c' is Whitespace: " + Character.isWhitespace('c'));
        System.out.println("'\\t' is Whitespace: " + Character.isWhitespace('\t'));
        System.out.println("'\\n' is Whitespace: " + Character.isWhitespace('\n'));
        System.out.println("' ' is Whitespace: " + Character.isWhitespace(' '));
    }

    public static void Character_isUpperCase() {
        System.out.println("'A' isUpperCase: " + Character.isUpperCase('A'));
        System.out.println("'a' isUpperCase: " + Character.isUpperCase('a'));
    }

    public static void Character_isLowerCase() {
        System.out.println("'B' isLowerCase: " + Character.isLowerCase('A'));
        System.out.println("'b' isLowerCase: " + Character.isLowerCase('b'));
    }

    public static void Character_toUpperCase() {
        System.out.println("'E' toUpperCase: " + Character.toUpperCase('E'));
        System.out.println("'e' toUpperCase: " + Character.toUpperCase('e'));
    }

    public static void Character_toLowerCase() {
        System.out.println("'E' toLowerCase: " + Character.toLowerCase('E'));
        System.out.println("'e' toLowerCase: " + Character.toLowerCase('e'));
    }

    public static void Character_toString() {
        System.out.println("'t' toString: " + Character.toString('t'));
        System.out.println("'7' toString: " + Character.toString('7'));
    }

    public static void main(String[] args) {
        Character_isLetter();
        Character_isDigit();
        Character_isWhitespace();
        Character_isUpperCase();
        Character_isLowerCase();
        Character_toUpperCase();
        Character_toLowerCase();
        Character_toString();
    }
}
