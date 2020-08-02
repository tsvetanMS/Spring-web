package softuni.emuseum.models.service;

public class ResistorServiceModel {

    private Long id;
    private String pictureID;
    private String description;
    private String type;
    private String resistance;
    private String maxPower;
    private String precision;

    public ResistorServiceModel() {
    }

    public ResistorServiceModel(String pictureID, String description, String type, String resistance, String maxPower, String precision) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.resistance = resistance;
        this.maxPower = maxPower;
        this.precision = precision;
    }

//-------------------------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
