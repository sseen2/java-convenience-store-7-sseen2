package store;

import java.time.LocalDate;

public class Promotion implements ConvenienceStore {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = DateParser.parseDate(startDate);
        this.endDate = DateParser.parseDate(endDate);
    }
}
