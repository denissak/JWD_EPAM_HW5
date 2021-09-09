package bean;

import java.io.Serializable;
import java.util.Objects;

public class Type implements Serializable {

    private boolean isPeriferal;
    private int energyConsumption;
    private boolean isCooler;
    private String groupofcomponent;
    private String port;

    public Type() {
    }

    public boolean getIsPeriferal() {
        return isPeriferal;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public boolean getIsCooler() {
        return isCooler;
    }

    public String getGroupofcomponent() {
        return groupofcomponent;
    }

    public String getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return isPeriferal == type.isPeriferal &&
                energyConsumption == type.energyConsumption &&
                isCooler == type.isCooler &&
                groupofcomponent == type.groupofcomponent &&
                port == type.port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPeriferal, energyConsumption, isCooler, groupofcomponent, port);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type{");
        sb.append("isPeriferal=");
        sb.append(isPeriferal);
        sb.append(", energyConsumption=");
        sb.append(energyConsumption);
        sb.append(", isCooler=");
        sb.append(isCooler);
        sb.append(", groupofcomponent=");
        sb.append(groupofcomponent);
        sb.append(", port=");
        sb.append(port);
        sb.append('}');

        return sb.toString();
    }

    public static Builder create(){
        return new Type().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setIsPeriferal(boolean isPeriferal) {
            Type.this.isPeriferal = isPeriferal;
            return this;
        }

        public Builder setEnergyConsumption(int energyConsumption) {
            Type.this.energyConsumption = energyConsumption;
            return this;
        }

        public Builder setIsCooler(boolean isCooler) {
            Type.this.isCooler = isCooler;
            return this;
        }
        public Builder setGroupofcomponent(String groupofcomponent) {
            Type.this.groupofcomponent = groupofcomponent;
            return this;
        }
        public Builder setPort(String port) {
            Type.this.port = port;
            return this;
        }

        public Type build(){
            return Type.this;
        }
    }

}
