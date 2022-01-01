import content.GenerateContent;
import huffman.Huffman;
import lzw.LempelZivWelch;
import utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Checker {
    public static void main(String[] args) {
        checkInput();
        readTests();
    }

    private static void checkInput(){

        StringBuilder path = new StringBuilder(getCurrentPath());
        path.append("/src/in");

        try (Stream<Path> walk = Files.walk(Paths.get(path.toString()))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
            if(result .size() < 20){ //change this number to change the number of tests created
                for(int i = 1; i <= 20; i++){ // and this number as well
                    String content;
                    if(i % 2 == 1){
                        content = GenerateContent.typeWords(i);
                    }else{
                        content = GenerateContent.typeRandom(i);
                    }
                    Files.write(Path.of(Paths.get(path + "/test" + i) + ".in"),content.getBytes());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readTests(){
        String path = getCurrentPath();


        try (Stream<Path> walk = Files.walk(Paths.get(path + "/src/in"))) {

            List<Path> inputTests = walk.collect(Collectors.toList());

            for(int i = 1; i < inputTests.size(); i++){
                Path test = inputTests.get(i);
                String noTest = test.toString().replaceAll("[^0123456789]","");
                executeTest(test,Integer.valueOf(noTest));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentPath(){
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

    private static void executeTest(Path testPath, Integer testNo){
        String path = getCurrentPath();
        StringBuilder outputPath = new StringBuilder(path);
        outputPath.append("/src/out/test").append(testNo);
        try {
            Files.createDirectories(Path.of(outputPath.toString()));
            Path huffmanOutput = Paths.get(outputPath + "/huffman.out");
            Path lzwOutput = Paths.get(outputPath + "/lzw.out");


            String inputContent = Files.readString(testPath, StandardCharsets.US_ASCII);

            Huffman huffman = new Huffman(inputContent);
            String huffmanCompress = huffman.encode();
            BitSet huffmanBitSet = Utils.createBitSet(huffmanCompress);

            List<Integer> lzwResult = LempelZivWelch.encode(inputContent);
            BitSet lzwBitSet = Utils.createBitSet(lzwResult);

            Files.write(huffmanOutput,huffmanBitSet.toByteArray());
            Files.write(lzwOutput, lzwBitSet.toByteArray());

            long inputSize = Files.size(testPath);
            System.out.println("Test " + testNo + " with size of " + inputSize);
            boolean huffmanStatus = validateHuffman(inputContent,huffmanCompress,huffman);
            boolean lzwStatus =  validateLZW(inputContent,lzwResult);

            if(huffmanStatus){
                System.out.println("        Huffman compressed file to " + Files.size(huffmanOutput));
            }else{
                System.out.println("        Huffman.....FAIL");
            }

            if(lzwStatus){
                System.out.println("        LZW compressed file to " + Files.size(lzwOutput));
            }else{
                System.out.println("        LZW.........FAIL");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateHuffman(String input, String result, Huffman huffman){


        String decode = huffman.decode(result);
        return input.equals(decode);
    }

    private static boolean validateLZW(String input, List<Integer> result){
        String decode = LempelZivWelch.decode(result);
        return input.equals(decode);

    }
}
