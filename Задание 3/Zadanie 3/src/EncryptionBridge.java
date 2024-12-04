import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class EncryptionBridge {

    // Абстракция
    public interface Encryption {
        String encrypt(String data);
        String decrypt(String encryptedData);
    }

    // Реализация
    public static abstract class Algorithm {
        public abstract byte[] encryptData(byte[] data);
        public abstract byte[] decryptData(byte[] encryptedData);
    }

    // Конкретные реализации алгоритмов
    public static class AESAlgorithm extends Algorithm {
        private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
        private static final int KEY_SIZE = 128;
        private static final byte[] IV = new byte[16]; // Initialization vector
        private SecretKey secretKey;

        public AESAlgorithm(String key) {
            try {
                // Генерация ключа
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(KEY_SIZE);
                this.secretKey = keyGenerator.generateKey();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Ошибка инициализации AES: " + e.getMessage());
            }
        }

        @Override
        public byte[] encryptData(byte[] data) {
            try {
                // Создание шифра
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV));

                // Шифрование данных
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка шифрования AES: " + e.getMessage());
            }
        }

        @Override
        public byte[] decryptData(byte[] encryptedData) {
            try {
                // Создание шифра
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV));

                // Дешифрование данных
                return cipher.doFinal(encryptedData);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка дешифрования AES: " + e.getMessage());
            }
        }
    }

    public static class RSAAlgorithm extends Algorithm {
        private PublicKey publicKey;
        private PrivateKey privateKey;

        public RSAAlgorithm() {
            try {
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
                generator.initialize(2048);
                KeyPair keyPair = generator.generateKeyPair();
                publicKey = keyPair.getPublic();
                privateKey = keyPair.getPrivate();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Ошибка инициализации RSA: " + e.getMessage());
            }
        }

        @Override
        public byte[] encryptData(byte[] data) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка шифрования RSA: " + e.getMessage());
            }
        }

        @Override
        public byte[] decryptData(byte[] encryptedData) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return cipher.doFinal(encryptedData);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка дешифрования RSA: " + e.getMessage());
            }
        }
    }

    public static class SHAAlgorithm extends Algorithm {
        private final String algorithm;

        public SHAAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        public byte[] encryptData(byte[] data) {
            try {
                MessageDigest digest = MessageDigest.getInstance(algorithm);
                return digest.digest(data);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Ошибка хеширования " + algorithm + ": " + e.getMessage());
            }
        }

        @Override
        public byte[] decryptData(byte[] encryptedData) {
            throw new UnsupportedOperationException("Дешифрование хеша невозможно");
        }
    }

    // "Мост" между абстракцией и реализацией
    public static class Bridge implements Encryption {
        private Algorithm algorithm;

        public Bridge(Algorithm algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        public String encrypt(String data) {
            return bytesToHex(algorithm.encryptData(data.getBytes()));
        }

        @Override
        public String decrypt(String encryptedData) {
            try {
                return new String(algorithm.decryptData(hexStringToByteArray(encryptedData)));
            } catch (UnsupportedOperationException e) {
                return e.getMessage();
            }
        }

        // Вспомогательные методы для конвертации
        private String bytesToHex(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }

        private byte[] hexStringToByteArray(String s) {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            return data;
        }
    }

    public static void main(String[] args) {
        Encryption aesEncryption = new Bridge(new AESAlgorithm("secretKey"));
        Encryption rsaEncryption = new Bridge(new RSAAlgorithm());
        Encryption shaEncryption = new Bridge(new SHAAlgorithm("SHA-256"));

        String data = "Secret message";

        String encryptedAES = aesEncryption.encrypt(data);
        String decryptedAES = aesEncryption.decrypt(encryptedAES);
        System.out.println("AES - Зашифровано: " + encryptedAES);
        System.out.println("AES - Расшифровано: " + decryptedAES);

        String encryptedRSA = rsaEncryption.encrypt(data);
        String decryptedRSA = rsaEncryption.decrypt(encryptedRSA);
        System.out.println("RSA - Зашифровано: " + encryptedRSA);
        System.out.println("RSA - Расшифровано: " + decryptedRSA);

        String encryptedSHA = shaEncryption.encrypt(data);
        String decryptedSHA = shaEncryption.decrypt(encryptedSHA);
        System.out.println("SHA - Хеш: " + encryptedSHA);
        System.out.println("SHA - 'Расшифровано': " + decryptedSHA); // Вывод сообщения об ошибке
    }
}