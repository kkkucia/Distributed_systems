import logging

import SmartHome
from interactiveShell.handlers.device_commands_handler import DeviceCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class LightBulbCommandsHandler(DeviceCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)
        self.logger = logging.getLogger(__name__)

    def handle(self, command):
        proxy = SmartHome.LightBulbPrx.checkedCast(self.get_proxy(command.device))
        if proxy is not None:
            if command.action == "getBrightness":
                proxy.ice_twoway()
                brightness = proxy.getBrightness()
                self.logger.info(f"The {command.device} has brightness set on {brightness}%")
                return True
            elif command.action == "setBrightness":
                try:
                    proxy.ice_oneway()
                    brightness = command.arguments[0]
                    set_brightness = proxy.setBrightness(int(brightness))
                    self.logger.info(f"Set {command.device} brightness to {set_brightness}%")
                except SmartHome.BrightnessOutOfRangeException:
                    self.logger.error("BrightnessOutOfRangeException: The range for brightness is from 0 to 100%")
                except SmartHome.DeviceIsOffException:
                    self.logger.error("DeviceIsOffException: First, turn on the device.")
                return True
            elif command.action == "getColour":
                proxy.ice_twoway()
                colour = proxy.getColour()
                self.logger.info(f"The {command.device} has {colour} colour set.")
                return True
        self.logger.error("Invalid command. Cannot find device.")
        return True
