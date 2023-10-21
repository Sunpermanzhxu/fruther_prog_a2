this project is written in vs code

java version: java 20 2023-03-21
JavaFX version: 21
database used: SQLiteStudio

// install and run
unsip and go to 
Run > Run without debugging

// OO design ------------------------
files are separted into 
    controller
        (controller for each view)
    model
    service
        DBService
        PostService
        UserService
    utility
    view
        (the UI pages)

each model will have a service related to it
    the services can get data from DBService and comiple to variables

each view will have their own controller
    the controllers can send request to services and get data and instrucston to change UI

in the main of APP
    the UIs are switched by the states of application usage
    during each swithch
        the related controller will be in action and be assigned the services stored in main
    when necessaru, a user is stored in main and will be passed through the controller and service file

Posts are never really stored as the database is aviliable