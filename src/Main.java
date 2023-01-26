import java.util.*;

public class Main {
    static Map<Integer, String> hashmap = new HashMap<>();
    static {
        hashmap.put(100, "C");
        hashmap.put(90, "XC");
        hashmap.put(50, "L");
        hashmap.put(40, "XL");
        hashmap.put(10, "X");
        hashmap.put(9, "IX");
        hashmap.put(5, "V");
        hashmap.put(4, "IV");
        hashmap.put(1, "I");
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String example = scanner.nextLine();
        System.out.println(calc(example));
    }

    public static String calc(String input) throws Exception {

        ArrayList<Integer> arrayListNumbers = new ArrayList<>(hashmap.keySet());
        ArrayList<String> arrayListRom = new ArrayList<>();
        Collections.sort(arrayListNumbers, (o1, o2) -> o2-o1);

        for(int i : arrayListNumbers){
            arrayListRom.add(hashmap.get(i));
        }

        int a = 0, b = 0, c = 0;

        String[] array = input.split(" ");
        if(array.length>3) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        if(array.length<3) throw new Exception("строка не является математической операцией");

        String deistvie = array[1];

        if(isNumber(array[0]) && isNumber(array[2])) {
            a = Integer.parseInt(array[0]);
            b = Integer.parseInt(array[2]);
            switch (deistvie){
                case "-": c = a-b; break;
                case "+": c = a+b; break;
                case "*": c = a*b; break;
                case "/": c = a/b; break;
            }
            return String.valueOf(c);
        }
//---------------------------------------------------------------------------------------------------------------------

        StringBuilder sb = new StringBuilder();
        if(!isNumber(array[0]) && !isNumber(array[2])){
            a = RomtoArab(array[0]);
            b = RomtoArab(array[2]);
            switch (deistvie){
                case "-": c = a-b; break;
                case "+": c = a+b; break;
                case "*": c = a*b; break;
                case "/": c = a/b; break;
            }
            if(c<=0) throw new Exception("в римской системе нет отрицательных чисел");
            for(Integer key : arrayListNumbers){
                while(c>=key){
                    c-=key;
                    sb.append(hashmap.get(key));
                }
            }
            return sb.toString();
        }
        else throw new Exception("используются одновременно разные системы счисления");
    }


    public static boolean isNumber(String number){
        try{
            Integer.parseInt(number);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static int RomtoArab(String number){
        int a = 0;
        if(hashmap.containsValue(number)){
            for(Map.Entry<Integer, String> set:hashmap.entrySet()){
                if(set.getValue().equals(number)) a = set.getKey();
            }
        }
        else{
            for(String ch: number.split("")){
                for(Map.Entry<Integer,String> set: hashmap.entrySet()){
                    if(set.getValue().equals(ch)) a += set.getKey();
                }
            }
        }
        return a;
    }
}