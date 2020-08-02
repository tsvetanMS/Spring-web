package softuni.emuseum.models.responce;

public class CapacitorParamsResponseModel {

    private String type;
    private String capacitance;
    private String maxVoltage;

    public CapacitorParamsResponseModel() {
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
