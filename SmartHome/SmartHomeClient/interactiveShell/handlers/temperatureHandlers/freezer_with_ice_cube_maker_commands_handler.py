import logging

import SmartHome
from interactiveShell.handlers.temperatureHandlers.fridge_commands_handler import FridgeCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class FreezerWithIceCubeMakerCommandsHandler(FridgeCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)
        self.logger = logging.getLogger(__name__)

    def handle(self, command):
        proxy = SmartHome.FreezerWithIceCubeMakerPrx.checkedCast(self.get_proxy(command.device))
        if proxy is not None:
            if command.action == "getCubesMakerCapacity":
                proxy.ice_twoway()
                cubes_maker_capacity = proxy.getCubesMakerCapacity()
                self.logger.info(f"The maximum capacity for {command.device} is {cubes_maker_capacity} ice cubes.")
                return True

            elif command.action == "getCubesQuantity":
                proxy.ice_twoway()
                cubes_maker_quantity = proxy.getCubesQuantity()
                self.logger.info(f"The {command.device} has {cubes_maker_quantity} ice cubes.")
                return True

            elif command.action == "makeIceCubes":
                proxy.ice_oneway()
                try:
                    cubes_to_make_quantity = command.arguments[0]
                    produced_ice_cubes = proxy.makeIceCubes(int(cubes_to_make_quantity))
                    self.logger.info(f"After production {cubes_to_make_quantity} ice cubes. The {command.device} has"
                                     f" {produced_ice_cubes} ice cubes ready to use.")
                except SmartHome.QuantityOfIceCubesMustBeGraterThanZeroException:
                    self.logger.error("Quantity of ice cubes must be grater than zero.")
                except SmartHome.DeviceIsOffException:
                    self.logger.error("DeviceIsOffException: First, turn on the device.")
                return True

            elif command.action == "takeIceCubes":
                proxy.ice_oneway()
                try:
                    cubes_quantity = command.arguments[0]
                    given_cubes_quantity = proxy.takeIceCubes(int(cubes_quantity))
                    self.logger.info(f"The {command.device} give you {given_cubes_quantity} ice cubes.")
                except SmartHome.LackOfIceCubesException:
                    self.logger.error("Lack of ice cubes. Make the ice cubes first.")
                except SmartHome.QuantityOfIceCubesMustBeGraterThanZeroException:
                    self.logger.error("Quantity of ice cubes must be grater than zero.")
                except SmartHome.DeviceIsOffException:
                    self.logger.error("DeviceIsOffException: First, turn on the device.")
                return True
        self.logger.error("Invalid command. Cannot find device.")
        return True
