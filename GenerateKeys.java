import java.nio.file.*;
import java.security.*;
import java.util.Base64;

public class GenerateKeys {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        Path folder = Paths.get("src/main/resources/keys/local-only");
        Files.createDirectories(folder);

        String privateKey = "-----BEGIN PRIVATE KEY-----\n"
                + Base64.getMimeEncoder(64, new byte[]{'\n'})
                .encodeToString(pair.getPrivate().getEncoded())
                + "\n-----END PRIVATE KEY-----\n";

        String publicKey = "-----BEGIN PUBLIC KEY-----\n"
                + Base64.getMimeEncoder(64, new byte[]{'\n'})
                .encodeToString(pair.getPublic().getEncoded())
                + "\n-----END PUBLIC KEY-----\n";

        Files.writeString(folder.resolve("private_key.pem"), privateKey);
        Files.writeString(folder.resolve("public_key.pem"), publicKey);

        System.out.println("RSA keys generated successfully!");
    }
}
