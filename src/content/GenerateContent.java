package content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GenerateContent {
    public static String typeWords(int n) {
        Path currentRelativePath = Paths.get("");
        StringBuilder path = new StringBuilder(currentRelativePath.toAbsolutePath().toString());
        path.append("/src/content/words.txt");
        List<String> words;
        try {
            words = Files.readAllLines(Paths.get(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n * 250; i++){
            String newWord = words.get(random.nextInt(words.size()));
            sb.append(newWord).append(" ");
        }
        return sb.toString();
    }

    public static String typeRandom(int n) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= n * 250; i++){
            sb.append((char)random.nextInt(33,126));
        }
        return sb.toString();
    }
}
