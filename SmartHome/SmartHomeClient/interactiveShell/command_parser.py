import logging

from interactiveShell.handlers.control_panel_commands_handler import ControlPanelHandler
from interactiveShell.handlers.device_commands_handler import DeviceCommandsHandler
from interactiveShell.handlers.lightHandlers.light_bulb_commands_handler import LightBulbCommandsHandler
from interactiveShell.handlers.lightHandlers.light_bulb_led_commands_handler import LightBulbLedCommandsHandler
from interactiveShell.handlers.temperatureHandlers.freezer_with_ice_cube_maker_commands_handler import \
    FreezerWithIceCubeMakerCommandsHandler
from interactiveShell.handlers.temperatureHandlers.fridge_with_shopping_list_commands_handler import \
    FridgeWithShoppingListCommandsHandler
from interactiveShell.handlers.temperatureHandlers.temperature_sensor_commands_handler import \
    TemperatureSensorCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class CommandParser:
    def __init__(self, communicator):
        self.logger = logging.getLogger(__name__)
        self.command_handlers = {
            "getMode": DeviceCommandsHandler(communicator),
            "setMode": DeviceCommandsHandler(communicator),

            "getBrightness": LightBulbCommandsHandler(communicator),
            "getColour": LightBulbCommandsHandler(communicator),
            "setBrightness": LightBulbCommandsHandler(communicator),

            "setLedColour": LightBulbLedCommandsHandler(communicator),

            "getTemperature": TemperatureSensorCommandsHandler(communicator),
            "setTemperature": TemperatureSensorCommandsHandler(communicator),
            "getTemperatureSensorLimits": TemperatureSensorCommandsHandler(communicator),
            "setTemperatureLimits": TemperatureSensorCommandsHandler(communicator),

            "getCubesMakerCapacity": FreezerWithIceCubeMakerCommandsHandler(communicator),
            "getCubesQuantity": FreezerWithIceCubeMakerCommandsHandler(communicator),
            "takeIceCubes": FreezerWithIceCubeMakerCommandsHandler(communicator),
            "makeIceCubes": FreezerWithIceCubeMakerCommandsHandler(communicator),

            "getShoppingList": FridgeWithShoppingListCommandsHandler(communicator),
            "addShoppingListProductRecord": FridgeWithShoppingListCommandsHandler(communicator),
            "removeShoppingListProductRecord": FridgeWithShoppingListCommandsHandler(communicator),
            "clearShoppingList": FridgeWithShoppingListCommandsHandler(communicator),

            "exitControlPanel": ControlPanelHandler(communicator),
        }

    def parse_command(self, command):
        if command:
            handler = self.command_handlers.get(command.action)
            if handler:
                return handler.handle(command)
            else:
                self.logger.error("Invalid command: SmartHome functionality not found.")
        else:
            self.logger.error("Exception: command not found.")
        return True
