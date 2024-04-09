package demo.mo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public final class JacksonUtil {
    private JacksonUtil() {
    }
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * JavaモデルインスタンスからJSON文字列に変換する
     * @param obj
     * @return JSON文字列
     */
    public static String bean2Json(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON文字列からJavaモデルインスタンスに変換する
     * @param <T>
     * @param jsonStr
     * @param objClass
     * @return Javaモデルインスタンス
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        try {
            return mapper.readValue(jsonStr, objClass);
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
    }
}
