import logging

import SmartHome
from interactiveShell.handlers.lightHandlers.light_bulb_commands_handler import LightBulbCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class LightBulbLedCommandsHandler(LightBulbCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)
        self.logger = logging.getLogger(__name__)
        self.colours = {"WHITE": SmartHome.LedColour.WHITE,
                        "YELLOW": SmartHome.LedColour.YELLOW,
                        "RED": SmartHome.LedColour.RED,
                        "GREEN": SmartHome.LedColour.GREEN,
                        "BLUE": SmartHome.LedColour.BLUE,
                        "ORANGE": SmartHome.LedColour.ORANGE,
                        "PINK": SmartHome.LedColour.PINK}

    def handle(self, command):
        proxy = SmartHome.LightBulbLEDPrx.checkedCast(self.get_proxy(command.device))
        if proxy is not None:
            if command.action == "setLedColour":
                proxy.ice_oneway()
                colour = command.arguments[0]
                if colour in self.colours.keys():
                    try:
                        colour_enum = self.colours.get(colour)
                        set_colour = proxy.setLedColour(colour_enum)
                        self.logger.info(f"Set {command.device} colour to {set_colour}.")
                    except SmartHome.DeviceIsOffException:
                        self.logger.error("DeviceIsOffException: First, turn on the device.")
                else:
                    self.logger.error("ColourNotFoundError: Unknown colour value.")
                return True
        self.logger.error("Invalid command. Cannot find device.")
        return True
