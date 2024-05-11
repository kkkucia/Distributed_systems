package smarthome.devices.temperatureControl;

import SmartHome.*;

public class FridgeImpl extends TemperatureSensorImpl implements Fridge {

    public FridgeImpl(float temperature ) {
        super(new TemperatureLimits(-10.0F, 10.0F), temperature);
    }

}
