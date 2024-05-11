import Ice
import logging

from interactiveShell.command import Command
from interactiveShell.command_parser import CommandParser

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(module)s - %(message)s')


class ControlPanel:

    def __init__(self, communicator):
        self.logger = logging.getLogger(__name__)
        self.panel_available = True
        self.communicator = communicator
        self.command_parser = CommandParser(communicator)
        self.logger.info("Client control panel started... \nCommand templates: [devices]/[quit]/[device action "
                         "arguments(if needed)]")

    def run_client_loop(self):
        while self.panel_available:
            try:
                command = input("Enter command: ")
                self.logger.info("Command process. Command: " + str(command))
                self.panel_available = self.command_parser.parse_command(Command(command))
            except IndexError:
                self.logger.warning("Invalid command. Try again!\nCommand templates: [devices]/[quit]/[device action "
                                    "arguments(if needed)]")
                continue
            except Ice.ConnectionRefusedException:
                self.logger.error("Couldn't connect to the server. Check your server status.")
            except Exception as e:
                self.logger.error("Exception: " + str(e))
