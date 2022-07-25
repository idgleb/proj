import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String str; // строка вводимая пользователем в консоли
        Scanner in = new Scanner(System.in); // класс позволяющий запускать метод для запроса строки у пользователя в консоли

        do { //запрашиваем строку у пользователя
            System.out.print("Введите выражение: ");
            str = in.nextLine();
            str = calc(str);
            System.out.println(str);
        } while (!str.contains("break")) ; // пока метод calc не вернет break

        in.close(); // закрываем поток ввода с консоли
    }

    public static String calc(String input){

        class Arab{
            enum Edin { // перечисления римских едениц
                n, I, II, III, IV, V, VI, VII, VIII, IX, X
            }
            enum Dec {   // перечисления римских десятков
                n, X, XX, XXX, XL, L, LX, LXX, LXXX, XC, C
            }
            String IntToRome(int  i) { // метод класса Arab переводящий число int в строку римского числа
                Edin[] edin = Edin.values(); // создаем масив единиц из инама
                Dec[] dec = Dec.values(); // создаем масив десятков из инама
                int dec10=i/10; //получаем количество десятков
                int ed1=i-dec10*10; //получаем количество едениц
                String decStr = dec[dec10].toString(); //получаем римские десятки из инама
                String edStr = edin[ed1].toString(); //получаем римские единицы из инама
                if (decStr=="n") decStr = ""; //если десятков 0 то строка с десятками пустая
                if (edStr=="n") edStr = ""; //если едениц 0 то строка с еденицами пустая
                return decStr+edStr; //возвращаем строку римского числа(десятки+единицы)
            }
            int RomeToInt(String  i) { // метод класса Arab переводящий римские в арабские
                Edin edins = Edin.valueOf(i); // создаем переменную типа Edin(enum) и присваиваем ей значение инама
                return edins.ordinal();
                // return Integer.toString(i);
            }
        }

        String num1 = ""; // строка первого числа
        String num2 = ""; // строка второго числа
        int num11=0; // число1 в фомате int
        int num22=0; // число2 в фомате int
        int res = 0; // результат вычисления в фомате int
        boolean FlagRome = false; // флаг что это римские

        String str=input.trim(); //обрезаем пробелы в начале и конце введенной пользователем строки
        if (str.contains("+")){  // если строка содержит +
            num1= str.substring(0,str.indexOf("+")).trim(); // переменной num1 присваиваем все что до +
            num2=  str.substring(str.indexOf("+")+1).trim(); // переменной num2 присваиваем все что после +
        }
        if (str.contains("-")){ // если строка содержит -
            num1= str.substring(0,str.indexOf("-")).trim(); // переменной num1 присваиваем все что до -
            num2=  str.substring(str.indexOf("-")+1).trim(); // переменной num2 присваиваем все что после -
        }
        if (str.contains("*")){ // если строка содержит *
            num1= str.substring(0,str.indexOf("*")).trim(); // переменной num1 присваиваем все что до *
            num2=  str.substring(str.indexOf("*")+1).trim(); // переменной num2 присваиваем все что после *
        }
        if (str.contains("/")){ // если строка содержит /
            num1= str.substring(0,str.indexOf("/")).trim(); // переменной num1 присваиваем все что до /
            num2=  str.substring(str.indexOf("/")+1).trim(); // переменной num2 присваиваем все что после /
        }

            try{ //пробуем перевести строки к числам
                num11 = Integer.valueOf(num1);
                num22 = Integer.valueOf(num2);
            }
            catch (Exception e) { //если не получилось, значит там не числа а символы(возможно римские)
                try{ //пробуем перевести римские строки к числам
                FlagRome = true; //поднимаем флаг что это римские
                Arab arab1 = new Arab(); // создаем объект класса Arab
                num11 = arab1.RomeToInt(num1); // используя медод RomeToInt из класса Arab,
                num22 = arab1.RomeToInt(num2); // переводим римские в арабские
                }
                catch (Exception e2) { //если и это не получилось, значит там пользовател ввел не то
                    return ("break "+ "Нужно ввести два числа(либо римские, либо арабские) от 1 до 10 и между ними оператор +,-,*,/");
                }
            }

            if (!(num11>=1 && num11<=10 && num22>=1 && num22<=10)) return ("break "+ "Нужно ввести два числа от 1 до 10 и между ними оператор +,-,*,/");
            if (str.contains("+")) res=num11+num22;
            if (str.contains("-")) res=num11-num22;
            if (str.contains("*")) res=num11*num22;
            if (str.contains("/")) res=num11/num22;

            if (FlagRome) { //если числа римские
                Arab arab2 = new Arab(); // создаем объект класса Arab
                if (res<1) return ("break "+ "Результат меньше 1, а в римских нет таких чисел");
                return arab2.IntToRome(res); // возвращаем результат в римском представлении
            } else return Integer.toString(res); // возвращаем результат в арабском представлении

            //return "";

    }

}