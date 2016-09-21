package uk.co.sainsburys.exercise.model;

/**
 *  A class to hold the information the web scraper needs about each product.
 */
public class RipeFruit {

    private final String title;
    private final String description;
    private final double unitPrice;
    private final String size;

    /**
     * Builds a new RipeFruit
     * @param title Product title
     * @param description Product description
     * @param unitPrice Product unit price
     * @param size Product page link size
     */
    public RipeFruit(String title, String description, double unitPrice, String size) {
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RipeFruit ripeFruit = (RipeFruit) o;

        if (Double.compare(ripeFruit.unitPrice, unitPrice) != 0) return false;
        if (!title.equals(ripeFruit.title)) return false;
        if (!description.equals(ripeFruit.description)) return false;
        return size.equals(ripeFruit.size);

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

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

}
