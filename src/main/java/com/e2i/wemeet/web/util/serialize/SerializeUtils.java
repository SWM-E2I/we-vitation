package com.e2i.wemeet.web.util.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public abstract class SerializeUtils {

    private static final String KEY_DELIMITER = "-";

    private SerializeUtils() {
    }

    public static <T> String serialize(T object) throws IOException {
        byte[] serializeBytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(object);
                // serializedMember -> 직렬화된 member 객체
                serializeBytes = baos.toByteArray();
            }
        }

        // 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
        return Base64.getEncoder().encodeToString(serializeBytes);
    }

    public static <T> T deserialize(String serialized, Class<T> type)
        throws IOException, ClassNotFoundException {
        byte[] serializedBytes = Base64.getDecoder().decode(serialized);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                // 역직렬화된 Member 객체를 읽어온다.
                Object objectMember = ois.readObject();

                // 지정된 타입으로 캐스팅 후 반환
                return type.cast(objectMember);
            }
        }
    }

    public static String getMemberDetailKey(final String teamCode, final String phoneNumber) {
        return teamCode + KEY_DELIMITER + phoneNumber;
    }
}
