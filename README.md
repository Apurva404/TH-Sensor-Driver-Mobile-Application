# TH-Sensor-Driver-Mobile-Application
This application demonstrates how to implement Async tasks in Android.
This android app, which facilitates in generation and collection of temperature and humidity datasets. 
The user directs the app to collect a fixed number (user input) of data points. 
Few important features of the application are:
1. Provides the user a scroll view to monitor the progress of the number of datasets generated,
2. User can see the recently generated datasets in the respective text views along with the corresponding thread id of the async task generating the data,
3. Provides the user an interface to stop the data generation without closing the whole app.
4. Proper error handling by the app includes handling
    o	Bad/no input from user
    o Multiple clicks on Generate button 
    o	Click on cancel button while no data generation is in progress
5. Provides the user a number pad instead of a generic keyboard to enter the count of dataset to be generated.

