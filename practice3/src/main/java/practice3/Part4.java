package practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    enum DigestAlgorithm {

        MD2("MD2"),
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512");

        String algorithm;

        DigestAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.print(hash("password to hash", DigestAlgorithm.MD5.getAlgorithm()));

    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] hash = messageDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte digit : hash) {
            sb.append(String.format("%1$02X", digit));
        }
        return sb.toString();
    }

}
