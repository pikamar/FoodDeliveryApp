package fooddelivery.fooddelivery;

public class Restaurant {

    private long id;
    private String name;
    private String url;
    private String phone;
    private String deliveryTime;
    private String freeDeliveryFrom;
    private String freeDeliveryWithCard;
    private String cardPay;
    private String logoUrl;
    private String rating;

    public Restaurant(long id, String name, String url, String phone, String deliveryTime, String freeDeliveryFrom, String freeDeliveryWithCard, String cardPay, String logoUrl, String rating) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.deliveryTime = deliveryTime;
        this.freeDeliveryFrom = freeDeliveryFrom;
        this.freeDeliveryWithCard = freeDeliveryWithCard;
        this.cardPay = cardPay;
        this.logoUrl = logoUrl;
        this.rating = rating;
    }

    public Restaurant() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getFreeDeliveryFrom() {
        return freeDeliveryFrom;
    }

    public void setFreeDeliveryFrom(String freeDeliveryFrom) {
        this.freeDeliveryFrom = freeDeliveryFrom;
    }

    public String getFreeDeliveryWithCard() {
        return freeDeliveryWithCard;
    }

    public void setFreeDeliveryWithCard(String freeDeliveryWithCard) {
        this.freeDeliveryWithCard = freeDeliveryWithCard;
    }

    public String getCardPay() {
        return cardPay;
    }

    public void setCardPay(String cardPay) {
        this.cardPay = cardPay;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", phone='" + phone + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", freeDeliveryFrom='" + freeDeliveryFrom + '\'' +
                ", freeDeliveryWithCard='" + freeDeliveryWithCard + '\'' +
                ", cardPay='" + cardPay + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
