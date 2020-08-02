package softuni.emuseum.models.service;

public class CapacitorServiceModel {

    private Long id;
    private String pictureID;
    private String description;
    private String type;
    private String capacitance;
    private String maxVoltage;

    public CapacitorServiceModel() {
    }
//----------------------------------------------------------------------------------------------------------------------


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

    public String getCapacitance() {
        return capacitance;
    }

    public void setCapacitance(String capacitance) {
        this.capacitance = capacitance;
    }

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }
}
