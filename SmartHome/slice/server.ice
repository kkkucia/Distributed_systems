
#ifndef SMARTHOME
#define SMARTHOME

module SmartHome
{
  //General
  exception DeviceNotFoundException {};
  exception DeviceIsOffException {};
  exception ModeNotChangeException {};
  exception IndexOutOfRangeException {};

  enum Mode { ON, OFF };

  interface Device {
      idempotent Mode getMode();
      idempotent Mode setMode(Mode mode) throws ModeNotChangeException;
      idempotent bool isDeviceTurnOFF();
  };

  //Lamp & Bulbs
  exception BrightnessOutOfRangeException {};

  enum LedColour { WHITE, YELLOW, RED, GREEN, BLUE, ORANGE, PINK };

  interface LightBulb extends Device {
      idempotent int getBrightness();
      idempotent LedColour getColour();
      idempotent int setBrightness(int brightnessPercentage) throws BrightnessOutOfRangeException, DeviceIsOffException;
  };

  interface LightBulbLED extends LightBulb {
      idempotent LedColour setLedColour(LedColour colour) throws DeviceIsOffException;
  };

  //Temperature
  exception TemperatureOutOfSupportedScaleException {};
  exception TemperatureOutOfLimitException {};
  exception TemperatureLowerLimitIsGreaterThanUpperLimitException {};

  struct TemperatureLimits {
      float lowerLimit;
      float upperLimit;
  };

  interface TemperatureSensor extends Device {
      idempotent float getTemperature();
      idempotent float setTemperature(float temperature) throws TemperatureOutOfLimitException;
      idempotent TemperatureLimits getTemperatureSensorLimits();
      idempotent TemperatureLimits setTemperatureLimits(TemperatureLimits temperatureLimits) throws TemperatureOutOfSupportedScaleException, TemperatureLowerLimitIsGreaterThanUpperLimitException;
  };

  // Fridge
  exception LackOfIceCubesException{};
  exception QuantityOfIceCubesMustBeGraterThanZeroException{};

  enum ProductUnit { ml, l, g, kg, notSpecified };

  struct Product{
      string name;
      ProductUnit unit;
      int quantity;
  };

  struct ShoppingListProductRecord{
      int id;
      Product product;
  };

  sequence<ShoppingListProductRecord> ShoppingList;

  interface Fridge extends TemperatureSensor {
    };

  interface FridgeWithShoppingList extends Fridge {
      idempotent ShoppingList getShoppingList() throws DeviceIsOffException;
      ShoppingListProductRecord addShoppingListProductRecord(Product Pproduct) throws DeviceIsOffException;
      ShoppingListProductRecord removeShoppingListProductRecord(int idx) throws IndexOutOfRangeException, DeviceIsOffException;
      ShoppingList clearShoppingList() throws DeviceIsOffException;
  };

   interface FreezerWithIceCubeMaker extends Fridge{
       idempotent int getCubesMakerCapacity();
       idempotent int getCubesQuantity() throws DeviceIsOffException;
       int takeIceCubes(int cubesQuantity) throws LackOfIceCubesException, QuantityOfIceCubesMustBeGraterThanZeroException, DeviceIsOffException;
       int makeIceCubes(int cubesQuantity) throws QuantityOfIceCubesMustBeGraterThanZeroException, DeviceIsOffException;
   };
};

#endif
