package uk.co.sainsburys.exercise.model;

/**
 *  A class to hold the information the web scraper needs about each product.
 */
public class RipeFruit {

    private final String title;
    private final String description;
    private final float unitPrice;
    private final String size;

    /**
     * Builds a new RipeFruit
     * @param title Product title
     * @param description Product description
     * @param unitPrice Product unit price
     * @param size Product page link size
     */
    public RipeFruit(String title, String description, float unitPrice, String size) {
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "RipeFruit{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", unitPrice=" + unitPrice +
                ", size='" + size + '\'' +
                '}';
    }
}
