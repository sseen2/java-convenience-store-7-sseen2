package store.view;

import store.domain.ConvenienceStore;
import store.domain.Product;
import store.domain.Promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceFileReadView {
    private static final String LINE_SPLIT_STRING = ",";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";
    private static final String PRODUCTS_FILE_NAME = "products.md";

    public static List<ConvenienceStore> fileRead(String fileName) {
        try(InputStream inputStream = ResourceFileReadView.class.getClassLoader().getResourceAsStream(fileName);
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

    private static ConvenienceStore promotionsFileRead(List<String> promotion) {
        String name = promotion.get(0);
        int buy = Integer.parseInt(promotion.get(1));
        int get = Integer.parseInt(promotion.get(2));
        String startDate = promotion.get(3);
        String endDate = promotion.get(4);

        return new Promotion(name, buy, get, startDate, endDate);
    }

    private static ConvenienceStore productsFileRead(List<String> product) {
        String name = product.get(0);
        int price = Integer.parseInt(product.get(1));
        int quantity = Integer.parseInt(product.get(2));
        String promotion = product.get(3);

        return new Product(name, price, quantity, promotion);
    }
}
