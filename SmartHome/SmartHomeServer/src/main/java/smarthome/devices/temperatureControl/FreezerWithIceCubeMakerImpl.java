package smarthome.devices.temperatureControl;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class FreezerWithIceCubeMakerImpl extends FridgeImpl implements FreezerWithIceCubeMaker {
    private final int iceCubesMakerCapacity;
    private int currentIceCubesQuantity;


    public FreezerWithIceCubeMakerImpl(float temperature) {
        super(temperature);
        this.currentIceCubesQuantity = 0;
        this.iceCubesMakerCapacity = 30;
    }

    @Override
    public int takeIceCubes(int cubesQuantity, Current current) throws DeviceIsOffException,
            QuantityOfIceCubesMustBeGraterThanZeroException, LackOfIceCubesException {
        if(isDeviceTurnOFF(current)){
            throw new DeviceIsOffException();
        }
        if(cubesQuantity <= 0){
            throw new QuantityOfIceCubesMustBeGraterThanZeroException();
        }
        if(currentIceCubesQuantity - cubesQuantity < 0){
            throw new LackOfIceCubesException();
        }
        return 0;
    }

    @Override
    public int makeIceCubes(int cubesQuantity, Current current) throws QuantityOfIceCubesMustBeGraterThanZeroException,
            DeviceIsOffException {
        if(isDeviceTurnOFF(current)){
            throw new DeviceIsOffException();
        }
        if(cubesQuantity <= 0){
            throw new QuantityOfIceCubesMustBeGraterThanZeroException();
        }

        this.currentIceCubesQuantity = Math.min(cubesQuantity + this.currentIceCubesQuantity, iceCubesMakerCapacity);
        return this.currentIceCubesQuantity;
    }

    @Override
    public int getCubesMakerCapacity(Current current) {
        return iceCubesMakerCapacity;
    }

    @Override
    public int getCubesQuantity(Current current) throws DeviceIsOffException {
        if(isDeviceTurnOFF(current)){
            throw new DeviceIsOffException();
        }
        return currentIceCubesQuantity;
    }
}
