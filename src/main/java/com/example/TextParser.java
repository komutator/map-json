package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class TextParser {
    public void TextParser(){

    }

    public static String convertToJSON(String input) {
        // Подготовка
        String result = input.trim();

        // 1. Заменить { и } на JSON скобки
        result = result.replaceAll("\\{", "{");
        result = result.replaceAll("\\}", "}");

        // 2. Заменить "ключ = значение" на "ключ": значение
        //   (если значение - текст, обернуть в кавычки)
        Pattern p = Pattern.compile("(\\w[\\w\\s]*?)\\s*=\\s*([^\\n\\{\\}]*)");
        Matcher m = p.matcher(result);

        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String key = m.group(1).trim().replaceAll("\\s+", "_");
            String value = m.group(2).trim();

            // Проверить, нужно ли оборачивать значение в кавычки
            if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false") && !value.matches("-?\\d+(\\.\\d+)?")) {
                value = "\"" + value + "\"";
            }
            m.appendReplacement(sb, "\"" + key + "\": " + value);
        }
        m.appendTail(sb);

        // 3. Добавить запятые между полями (не очень строго, но работает)
        result = sb.toString();
        result = result.replaceAll("}(\\s*)\\{", "}, {");
        result = result.replaceAll("(\\d)\\s*(\\w)", "$1,\n\"$2");

        // 4. Обернуть общий результат в {}
        return "{\n" + result + "\n}";
    }


}
