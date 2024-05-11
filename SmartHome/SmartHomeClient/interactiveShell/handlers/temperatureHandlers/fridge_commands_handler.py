from interactiveShell.handlers.temperatureHandlers.temperature_sensor_commands_handler import \
    TemperatureSensorCommandsHandler


class FridgeCommandsHandler(TemperatureSensorCommandsHandler):
    def __init__(self, communicator):
        super().__init__(communicator)

    # currently the basic version of the fridge is equivalent temperature sensor placed inside the device
