// Дана строка sql-запроса:

// select * from students where "

// Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Пример данных для фильтрации приведены ниже в виде json-строки. 
// Если значение null, то параметр не должен попадать в запрос.

// Пример:

// {"name": "Ivanov", "country": "Russia", "city": "Moscow", "age": "null"}
// Напишите свой код в методе answer класса Answer. Метод answer принимает на вход два параметра:

// String QUERY - начало SQL-запроса
// String PARAMS - JSON с параметрами

class Answer {
    public static StringBuilder answer(String QUERY, String PARAMS) {
        StringBuilder name = new StringBuilder(QUERY);
        int count = 0;
        String valNull = "null";
        PARAMS = PARAMS.replace("{", " ").replace("}", " ").trim();

        String[] words = PARAMS.split(",");
        String[] array = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
            String[] ar = words[i].split(":");

            for (int j = 0; j < ar.length; j++) {
                ar[j] = ar[j].replace("\"", " ").trim();
            }

            if (!ar[1].equals(valNull)) {
                array[count] = (ar[0] + "=\'" + ar[1] + "\'");
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            if (i < count - 1) {
                name.append(array[i] + " and ");
            } else {
                name.append(array[i]);
            }
        }
        return name;
    }
}

public class Printer1 {
    public static void main(String[] args) {
        String QUERY = "";
        String PARAMS = "";

        if (args.length == 0) {
            QUERY = "select * from students where ";
            PARAMS = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"} ";
        } else {
            QUERY = args[0];
            PARAMS = args[1];
        }

        Answer ans = new Answer();
        System.out.println(ans.answer(QUERY, PARAMS));
    }
}