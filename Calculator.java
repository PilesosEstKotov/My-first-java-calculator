import java.util.HashMap;  //этот класс нужен чтобы присваивать значениям своё число
import java.util.Map;      //этот класс нужен чтобы с этими значениями взаимодействовать
import java.util.Scanner;  //этот класс нужен чтобы можно было вводить свои данные в программу

public class Calculator { // это я создаю класс в котором всё будет происходить

    private static final Map<Character, Integer> para = new HashMap<>(); //Это я создаю место в котором будут хранится мои пары арабское число\римское число
    private static final String[] rimskieChisla = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"}; //Это я создал массив

    static {                   //тут я присваиваю каждому значению массива своё число
        para.put('I', 1);
        para.put('V', 5);
        para.put('X', 10);
        para.put('L', 50);
        para.put('C', 100);
        para.put('D', 500);
        para.put('M', 1000);
    }
    
    //тут до меня наконец дошло что римские цифры работают не совсем так как арабские

    private static int RA(String rim) { //тут у меня функция RA с циклом for нам надо перебрать все эллеменнты массива римских чисел пока не попадём туда куда надо. По сути мы создадим новое число q эквивалентное римскому
        int q = 0;                        
        for (int x = 0; x < rim.length(); ++x) {
            if (x + 1 < rim.length() && para.get(rim.charAt(x)) < para.get(rim.charAt(x + 1))) {
                q -= para.get(rim.charAt(x));
            } else {
                q += para.get(rim.charAt(x));
            }
        }
        return q;
    }

    private static String AR(int arab) {  //тут у меня функция AR она такая же по смыслу но содержание чутьчуть другое потому что на выходе строка, тут мы наоборот пристреливаемся и ищем соответсвующий полученному числу эллемент массива
        StringBuilder c = new StringBuilder();
        int v = arab;                      
        for (int x = rimskieChisla.length - 1; x >= 0; --x) {
            while (v >= RA(rimskieChisla[x])) {
                c.append(rimskieChisla[x]);
                v -= RA(rimskieChisla[x]);
            }
        }
        return c.toString();
    }

    public static void main(String[] args) { //тут мы одновременно проверяем не больше ли число необходимого значения и используем по необходимости функию RA для того чтобы всё могло посчитатся
        Scanner s = new Scanner(System.in);
        System.out.print("Arabic (A) or Roman (R)?: ");
        char type = s.next().charAt(0);      //переменную type вводим чтобы программа знала с каким типом чисел мы работаем

        System.out.print("Введите первое число: ");
        int chislo1 = type == 'A' ? s.nextInt() : RA(s.next()); //тут мы либо присваиваем переменной Chislo1 введённое число сразу, либо прогоняем его через функцию RA чтобы с ним можно было работать
        if (chislo1 > 10) {
            System.out.println("больше десяти нельзя");
            return;
            
        }

        System.out.print("Введите второе число: ");
        int chislo2 = type == 'A' ? s.nextInt() : RA(s.next()); // зе сейм
        if (chislo2 > 10) {
            System.out.println("больше десяти нельзя");
            return;
        }

        System.out.print("Выберите операцию (+, -, *, /, S (возведение в степень)): ");
        char vibor = s.next().charAt(0);

        int resultat = 0;
        switch (vibor) {  //тут у меня инструкции что надо делать с введёнными символами 
            case '+':
                resultat = chislo1 + chislo2;
                break;
            case '-':
                resultat = chislo1 - chislo2;
                break;
            case '*':
                resultat = chislo1 * chislo2;
                break;
            case '/':
                resultat = chislo1 / chislo2;
                break;
            case 'S':
                resultat = (int) Math.pow(chislo1, chislo2); //Именно эта строчка объясняет почему у меня везде Int а не double
                break;
            default:
                System.out.println("Такое я не умею");
                break;
        }
                                           //а тут у меня ответы. Если надо выдать арабское чесло то он показывает переменную resultat, а если надо выдать римское то он сначала прогоняет нашу переменную через функцию AR
        if (type == 'R' && resultat < 0) {
            System.out.println("Римские числа не бывают отрицательными");
            return;
        }

        if (type == 'A') {
            System.out.println("Результат: " + resultat);
        } else {
            System.out.println("Результат: " + AR(resultat));  
        }
        s.close();}
}
   