# Car Maintenance Tracker

## Purpose:
The purpose of this project is to have a dedicated web application designed to help users keep track of their vehicle's maintenance and repair history. It allows users to record services performed, whether it be a maintenance or a repair job, while organizing records by mileage to help view service entries more easily.

## Setup Instuctions:
To use the web application:
  1. Clone the repository
     Open a terminal or command prompt, navigate to your preferred directory, and clone the repository by typing the following in your terminal:
      ```bash
     git clone <repository-url> car-maintenance-tracker
     ```
     Then navigate into the project directory:
     ```bash
     cd car-maintenance-tracker
     ```
     -Note: You could also skip those steps and clone the repository using its web URL under the green Code button above and entering it into Visual Studio Code.
  3. Open Visual Studio Code and install recommended extensions by going to the Extensions page and then typing '@recommended' in the search bar to view the workspace recommended extensions.
  4. Start the application
     Make sure the terminal is located in the project root directory. You can check your current location with
     ```bash
     pwd
     ```
     You may need to add "car-maintenance-tracker" to the end of your current location. So you should type "cd current location/car-maintenance-tracker" in the terminal.

     Run the application by entering the typing into your terminal:
     ```bash
     ./mvnw spring-boot:run
     ```
     Maven will compile the application and start the server.
  6. Open the application
     Once the application has started, open a web browser and go to:
     ```bash
     http://localhost:8080
     ```
     The Car Maintenance Tracker should now be available
  7. You can stop the application by pressing Control + C while in the terminal.
