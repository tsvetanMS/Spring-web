package softuni.emuseum.models.responce;

public class DiodeParamsResponseModel {

    private String type;
    private String maxVoltage;
    private String maxCurrent;

    public DiodeParamsResponseModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }
}
