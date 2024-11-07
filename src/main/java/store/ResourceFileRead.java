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

    public static List<ConvenienceStore> fileRead(String fileName) {
        try(InputStream inputStream = ResourceFileRead.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return fileLineRead(bufferedReader, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private static List<ConvenienceStore> fileLineRead(BufferedReader bufferedReader, String fileName) throws IOException {
        String fileLine;
        bufferedReader.readLine();
        List<ConvenienceStore> inputData = new ArrayList<>();
        while((fileLine = bufferedReader.readLine()) != null) {
            inputData.add(classificationFile(fileName, List.of(fileLine.split(LINE_SPLIT_STRING))));
        }

        return inputData;
    }

    private static ConvenienceStore classificationFile(String fileName, List<String> inputData) {
        if (fileName.equals(PROMOTIONS_FILE_NAME)) {
            return promotionsFileRead(inputData);
        }
        if (fileName.equals(PRODUCTS_FILE_NAME)) {
            return productsFileRead(inputData);
        }

        return null;
    }

    private static ConvenienceStore promotionsFileRead(List<String> inputData) {
        String name = inputData.get(0);
        int buy = Integer.parseInt(inputData.get(1));
        int get = Integer.parseInt(inputData.get(2));
        String startDate =inputData.get(3);
        String endDate = inputData.get(4);

        return new Promotion(name, buy, get, startDate, endDate);
    }

    private static ConvenienceStore productsFileRead(List<String> inputData) {
        String name = inputData.get(0);
        int price = Integer.parseInt(inputData.get(1));
        int quantity = Integer.parseInt(inputData.get(2));
        String promotion = inputData.get(3);

        return new Product(name, price, quantity, promotion);
    }
}
