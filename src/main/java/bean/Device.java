package bean;

import java.io.Serializable;
import java.util.Objects;

public class Device implements Serializable {

    private int id;
    private String name;
    private String origin;
    private float price;
    private Type type;
    private boolean isCritical;

    public Device() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public float getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public boolean getIsCritical() {
        return isCritical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device{");
        sb.append("id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", origin=");
        sb.append(origin);
        sb.append(", price=");
        sb.append(origin);
        sb.append(", price=");
        sb.append(price);
        sb.append(", type=");
        sb.append(type);
        sb.append(", isCritical=");
        sb.append(isCritical);
        return sb.toString();
    }

    public static Builder create(){
        return new Device().new Builder();
    }

    public class Builder{

        public Builder setId(int id){
            Device.this.id = id;
            return this;
        }

        public Builder setName(String name){
            Device.this.name = name;
            return this;
        }

        public Builder setOrigin(String origin){
            Device.this.origin = origin;
            return this;
        }

        public Builder setPrice(float price){
            Device.this.price = price;
            return this;
        }

        public Builder setType (Type type){
            Device.this.type = type;
            return this;
        }

        public Builder setIsCritical(boolean isCritical){
            Device.this.isCritical = isCritical;
            return this;
        }

        public Device build(){
            return Device.this;
        }

    }

}


