package fooddelivery.fooddelivery;

public class RestaurantCategory {

    // FIXME do we really need id for it?
    private long id;
    private long restaurantId;
    private long categoryId;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        return "RestaurantCategory{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", categoryId=" + categoryId +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }

    public RestaurantCategory(long id, String menuUrl, long categoryId, long restaurantId) {
        this.id = id;
        this.menuUrl = menuUrl;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
    }

    private String menuUrl;

    public RestaurantCategory() {
    }

    public RestaurantCategory(long id, long restaurantId, long categoryId) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

}
