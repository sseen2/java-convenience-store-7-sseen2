package store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceFileRead {
    private static final String LINE_SPLIT_STRING = ",";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";
    private static final String PRODUCTS_FILE_NAME = "products.md";

    public static void fileRead(String fileName) {
        try(InputStream inputStream = ResourceFileRead.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            fileLineRead(bufferedReader, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void fileLineRead(BufferedReader bufferedReader, String fileName) throws IOException {
        String fileLine;
        bufferedReader.readLine();
        while((fileLine = bufferedReader.readLine()) != null) {
            classificationFile(fileName, List.of(fileLine.split(LINE_SPLIT_STRING)));
        }
    }

    private static void classificationFile(String fileName, List<String> inputData) {
        if (fileName.equals(PROMOTIONS_FILE_NAME)) {
            promotionsFileRead(inputData);
        }
        if (fileName.equals(PRODUCTS_FILE_NAME)) {
            productsFileRead(inputData);
        }
    }

    private static void promotionsFileRead(List<String> inputData) {

    }

    private static void productsFileRead(List<String> inputData) {

    }
}
