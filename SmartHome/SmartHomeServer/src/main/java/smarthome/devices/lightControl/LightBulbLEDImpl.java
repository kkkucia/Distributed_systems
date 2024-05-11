package smarthome.devices.lightControl;

import SmartHome.DeviceIsOffException;
import SmartHome.LedColour;
import SmartHome.LightBulbLED;
import com.zeroc.Ice.Current;

public class LightBulbLEDImpl extends LightBulbImpl implements LightBulbLED {

    private LedColour colour;

    public LightBulbLEDImpl(int brightness, LedColour colour) {
        super(brightness);
        this.colour = colour;
    }

    @Override
    public LedColour setLedColour(LedColour colour, Current current) throws DeviceIsOffException {
        if(isDeviceTurnOFF(current)){
            throw new DeviceIsOffException();
        }
        this.colour = colour;
        return this.colour;
    }
}
