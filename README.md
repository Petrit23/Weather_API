# Java Spring Boot Weather API
Weather API built in Java using Spring boot that receives weather data from various sensors that report metrics such as temperature, humidity, wind, speed, etc.

<p align="right">(<a href="#top">back to top</a>)</p>

### Technology Stack

* Java 1.8
* Spring Boot
* H2 Java SQL in memory database
* JPA
* Maven 3.6.3
* Junit, Mockito

<p align="right">(<a href="#top">back to top</a>)</p>

### Project Setup

* Clone this repository and launch in your preferred IDE.
* Ensure you're using Java 1.8 and Maven version 3.6.3
* Run mvn clean install to build project, install dependencies to get it ready for start up.
* Navigate to WeatherapiApplication.java and run
* On application startup, the database is started and the data is loaded into the db based on the data.sql script. Tables are created based on entity definitions. When the server stops the database table instance is dropped.
* Once the application is running, the app should be available under the following URL: http://localhost:9090/api/
* The in memory database is accessible under the following URL:
http://localhost:9090/api/h2-console

<p align="right">(<a href="#top">back to top</a>)</p>

## Code coverage
<img width="896" alt="Screenshot 2023-02-07 at 21 57 19" src="https://user-images.githubusercontent.com/23060798/217376313-3ce5b9f1-edba-48f9-ba93-de5c1031114e.png">

## Endpoints Exposed

## API Health Check 
#### Check health - GET - http://localhost:9090/api/health
```
Successful connection to WeatherAPI app

````
## Sensor

### Register sensor - POST - http://localhost:8080/api/registerSensor
```
{
    "sensorId": "123`4",
    "country": "Ireland",
    "city": "Dublin"
   
}
```

__An attempt to register a sensor that already exists, generates the below response:__
```
Sensor with the following details:
 { sensor Id: 1234, country: Ireland, city: Dublin }
 already exists
```

### Retrieve all sensors - GET - http://localhost:8080/api/findSensors
```
[
    {
        "id": 1,
        "sensorId": 1234,
        "country": "IRELAND",
        "city": "DUBLIN"
    },
    {
        "id": 2,
        "sensorId": 4567,
        "country": "IRELAND",
        "city": "CORK"
    },
    {
        "id": 3,
        "sensorId": 8910,
        "country": "IRELAND",
        "city": "GALWAY"
    }
]
```

### Retrieve one or more sensors by sensorId - GET - http://localhost:8080/api/findSensors?sensorIds=1234, 4567
```
[
    {
        "id": 1,
        "sensorId": 1234,
        "country": "IRELAND",
        "city": "DUBLIN"
    },
    {
        "id": 2,
        "sensorId": 4567,
        "country": "IRELAND",
        "city": "CORK"
    }
]
```

## Weather
### Receive and record new weather data - POST - http://localhost:8080/api/weather
```
{
    "sensorId": 789,
    "temperature": 20,
    "humidity": 15,
    "windSpeed": 30
}
```

__If weather information was received from an unregistered sensor, the following response is returned__
```
Received weather data from unregistered sensor
 SensorId:  789
 Rejecting weather information supplied
 ```
 
### Query weather data by one or more (or all sensors) to include average data for requested metric - GET - E.G http://localhost:8080/api/metrics?sensorIds=1234,4567&temperature=true&humidity=true&fromDate=2023-01-01&toDate=2023-02-05&windSpeed=true
```
[
    {
        "sensorId": 1234,
        "averageTemperature": 7.0,
        "averageHumidity": 29.5,
        "averageWindSpeeds": 24.5
    },
    {
        "sensorId": 4567,
        "averageTemperature": 20.75,
        "averageHumidity": 23.25,
        "averageWindSpeeds": 18.75
    }
]
```
The above endpoint accepts sensorId, metric, date range as optional paramters to query the weather API.<br>
If no sensorId is specified it will include data for all registered sensors.<br>
If no specific metric is specified in the request then no data is returned.<br>
If no date range is specified it will calculate the average of the latest data loaded per sensor.<br>
Results are returned per sensor.<br>
Example request: Give me the average temperature and humidity for sensor 1234, for data loaded between 2023-01-01 and 2023-02-06:<br><br>
__GET - http://localhost:8080/api/metrics?sensorIds=1234&temperature=true&humidity=true&fromDate=2023-01-01&toDate=2023-02-06__ <br><br>

```
[
    {
        "sensorId": 1234,
        "averageTemperature": 7.6,
        "averageHumidity": 32.4
    }
]
```
Note, windspeed wasn't included in the response as this was not requested.
