import logging

import SmartHome
from interactiveShell.handlers.device_commands_handler import DeviceCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class TemperatureSensorCommandsHandler(DeviceCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)
        self.logger = logging.getLogger(__name__)

    def handle(self, command):
        proxy = SmartHome.TemperatureSensorPrx.checkedCast(self.get_proxy(command.device))
        if proxy is not None:
            if command.action == "getTemperature":
                proxy.ice_twoway()
                temperature = proxy.getTemperature()
                self.logger.info(f"The {command.device} has the {temperature} C")
                return True

            elif command.action == "setTemperature":
                proxy.ice_oneway()
                temperature = command.arguments[0]
                try:
                    set_temperature = proxy.setTemperature(float(temperature))
                    self.logger.info(f"Set {command.device} temperature to {set_temperature} C")
                except SmartHome.TemperatureOutOfLimitException:
                    self.logger.error("TemperatureOutOfLimitException: The temperature exceeded the set limit.")
                return True

            elif command.action == "getTemperatureSensorLimits":
                proxy.ice_twoway()
                temperature_limits = proxy.getTemperatureSensorLimits()
                self.logger.info(f"The {command.device} temperature limits are set to {temperature_limits}")
                return True

            elif command.action == "setTemperatureLimits":
                proxy.ice_oneway()
                temperature_lowerLimit = command.arguments[0]
                temperature_upperLimit = command.arguments[1]
                temperature_limits = SmartHome.TemperatureLimits(float(temperature_lowerLimit), float(temperature_upperLimit))
                try:
                    proxy.setTemperatureLimits(temperature_limits)
                    self.logger.info(f"Set {command.device} temperature limits. Lower limit: {temperature_lowerLimit} Upper limit: {temperature_upperLimit}")
                except SmartHome.TemperatureLowerLimitIsGreaterThanUpperLimitException:
                    self.logger.error("Temperature lower limit cannot be greater than upper limit exception.")
                except SmartHome.TemperatureOutOfSupportedScaleException:
                    self.logger.error("Temperature limits cannot exceeded the sensor supported scale. Supported scale "
                                      "is  Lower limit: -30 C  Upper limit: 40 C")
                return True
        self.logger.error("Invalid command. Cannot find device.")
        return True
