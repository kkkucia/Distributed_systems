import logging

import SmartHome
from interactiveShell.handlers.temperatureHandlers.fridge_commands_handler import FridgeCommandsHandler

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class FridgeWithShoppingListCommandsHandler(FridgeCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)
        self.logger = logging.getLogger(__name__)
        self.units = {"ml": SmartHome.ProductUnit.ml,
                      "l": SmartHome.ProductUnit.l,
                      "g": SmartHome.ProductUnit.g,
                      "kg": SmartHome.ProductUnit.kg,
                      "notSpecified": SmartHome.ProductUnit.notSpecified}

    def handle(self, command):
        proxy = SmartHome.FridgeWithShoppingListPrx.checkedCast(self.get_proxy(command.device))
        if command.action == "getShoppingList":
            proxy.ice_twoway()
            try:
                shopping_list = proxy.getShoppingList()
                self.logger.info(f"The shopping list for {command.device}: {shopping_list}")
            except SmartHome.DeviceIsOffException:
                self.logger.error("DeviceIsOffException: First, turn on the device.")
            return True

        elif command.action == "clearShoppingList":
            proxy.ice_oneway()
            try:
                proxy.clearShoppingList()
                self.logger.info(f"The shopping list for {command.device} is empty.")
            except SmartHome.DeviceIsOffException:
                self.logger.error("DeviceIsOffException: First, turn on the device.")
            return True

        elif command.action == "addShoppingListProductRecord":
            proxy.ice_oneway()
            try:
                product_name = command.arguments[0]
                quantity = command.arguments[1]
                unit = self.units.get(command.arguments[2], "notSpecified")
                product_to_add = SmartHome.Product(product_name, unit, int(quantity))
                added_product = proxy.addShoppingListProductRecord(product_to_add)
                self.logger.info(
                    f"The product: {added_product} added to shopping list.")
            except SmartHome.DeviceIsOffException:
                self.logger.error("DeviceIsOffException: First, turn on the device.")
            return True

        elif command.action == "removeShoppingListProductRecord":
            proxy.ice_oneway()
            try:
                product_idx = command.arguments[0]
                product = proxy.removeShoppingListProductRecord(int(product_idx))
                self.logger.info(f"The product: {product} was removed from shopping list.")
            except SmartHome.DeviceIsOffException:
                self.logger.error("DeviceIsOffException: First, turn on the device.")
            return True

        self.logger.error("Invalid command. Cannot find device.")
        return True

