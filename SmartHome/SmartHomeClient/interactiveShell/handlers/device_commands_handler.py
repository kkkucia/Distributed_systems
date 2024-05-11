import logging

import SmartHome

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class DeviceCommandsHandler:
    def __init__(self, communicator):
        self.communicator = communicator
        self.logger = logging.getLogger(__name__)
        self.modes = {"ON":  SmartHome.Mode.ON, "OFF":  SmartHome.Mode.OFF}

    def handle(self, command):
        proxy = SmartHome.DevicePrx.checkedCast(self.get_proxy(command.device))
        if proxy is not None:
            if command.action == "getMode":
                proxy.ice_twoway()
                mode = proxy.getMode()
                self.logger.info(f"The {command.device} is currently in the {mode} mode")
                return True
            elif command.action == "setMode":
                proxy.ice_oneway()
                mode = command.arguments[0]
                if mode in self.modes.keys():
                    try:
                        mode_enum = self.modes.get(mode)
                        set_mode = proxy.setMode(mode_enum)
                        self.logger.info(f"Set {command.device} mode to {set_mode}")
                    except SmartHome.ModeNotChangeException:
                        self.logger.info("The new mode is the same as the current one, not changing.")
                else:
                    self.logger.error("ModuleNotFoundError: Unknown mode value.")
                return True
        self.logger.error("Invalid command. Cannot find device.")
        return True

    def get_proxy(self, device_name):
        proxy = self.communicator.propertyToProxy(device_name + ".Proxy")
        return proxy
