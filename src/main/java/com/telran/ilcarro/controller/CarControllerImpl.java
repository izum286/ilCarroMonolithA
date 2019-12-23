package com.telran.ilcarro.controller;

import com.telran.ilcarro.service.CarService;
import com.telran.ilcarro.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    CarService carService;

    @Autowired
    FilterService filterService;

    //Так как это наш метод, я не знаю, какое ему описание описать, потому что я пока хз, для чего он =(
    @Override
    public ResponseEntity getSingleCar(String carId) {
        try {
            UUID tmp = UUID.fromString(carId);
            String response = carService.getSingleCar(tmp);
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(404).body("Car with id: " + carId + " not found.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Bad request. Message:" + ex.getMessage());
        }
    }


    /**
     * A method that intercepts a request from url "/car/popularcar" and passes it to carService
     *
     * @return ResponseEntity with code 200 and body with 10 FullCarDto in JSON format
     * or
     * ResponseEntity with code 404 if cars not found
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @GetMapping("/car/popularcar")
    public ResponseEntity getPopularCar() {
        try {
            String response = carService.getPopularCar();
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(404).body("Cars not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + ex.getMessage());
        }
    }


    /**
     * A method that intercepts a request from url "/car/{id}"
     * where {id} is carId
     * and passes it to carService
     *
     * @param carId
     * @return ResponseEntity with code 200 and body with FullCarDto in JSON format
     * or
     * ResponseEntity with code 400 and exception message in body of response
     * or
     * ResponseEntity with code 404 if car with {id} not found/exists
     */
    @Override
    @GetMapping("/car/{id}")
    public ResponseEntity getSingleFullCar(@PathVariable("id") String carId) {
        try {
            UUID tmp = UUID.fromString(carId);
            String response = carService.getSingleFullCar(tmp);
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(404).body("Car with id: " + carId + " not found.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Bad request. Message: " + ex.getMessage() + ex.getCause());
        }
    }

    //TODO which list is returned? ------------------------------------------------------------------------>

    /**
     * A method that intercepts a request from url "/car/findcar" with querryParams where
     * "coordinates={location}" is car coordinates in format "54.64345,43.24135",                                                               |
     * "areaRadius={radius}" is required area in meters,                                                            |      |
     * and passes it to carService
     *
     * @param location
     * @param area
     * @return ResponseEntity with code 200 and body with someList FullCarDTOs in JSON format  <------------
     * or
     * ResponseEntity with code 404 if cars not found
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @GetMapping("/car/findcar?coordinates=coordinates&areaRadius=radius")
    public ResponseEntity findByLocationAndArea(@RequestParam(name = "coordinates") String location,
                                                @RequestParam(name = "areaRadius") String area) {
        try {
            Double tmp = Double.parseDouble(area);
            String response = carService.findByLocationAndArea(location, tmp);
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(400).body("Wrong location format");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + ex.getMessage());
        }
    }

    /**
     * A method that intercepts a request from url "car/findcar" with querryParams where
     * "location={location}" is car coordinates in format "54.64345,43.24135",                                                                   |
     * "from={startDate}" is required startDate in format 21/12/2021,                                                            |
     * "till={endDate}" is required endDate in format 21/12/2021
     * and passes it to carService
     *
     * @param location
     * @param dateFrom
     * @param dateTo
     * @return ResponseEntity with code 200 and body someList FullCarDTOs in JSON format
     * or
     * ResponseEntity with code 404 if cars not found
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @GetMapping("car/findcar@location=location&from=from&till=till")
    public ResponseEntity findByLocationAndDates(@RequestParam(name = "location") String location,
                                                 @RequestParam(name = "from") String dateFrom,
                                                 @RequestParam(name = "till") String dateTo) {
        try {
            String response = carService.findByLocationAndDates(location, dateFrom, dateTo);
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(400).body("Invalid location name");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + ex.getMessage());
        }
    }

    /**
     * A method that intercepts a request from url "car/findcar" with querryParams where
     * "location={location}" is car coordinates in format "54.64345,43.24135",                                                                   |
     * "dateFrom={startDate}" is required startDate in format 21/12/2021,                                                            |
     * "dateTill={endDate}" is required endDate in format 21/12/2021,
     * "priceFrom={startPrice}" is required price from in format double,                                                            |
     * "priceTill={endPrice}" is required price to in format double,
     * and passes it to carService
     *
     * @param location
     * @param dateFrom
     * @param dateTo
     * @param priceFrom
     * @param priceTo
     * @return ResponseEntity with code 200 and body someList FullCarDTOs in JSON format
     * or
     * ResponseEntity with code 404 if cars not found
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @GetMapping("car/findcar?location=location&dateFrom=startDate&dateTo=endDate&priceFrom=startPrice&priceTill=endPrice")
    public ResponseEntity findByLocationDatePrice(@RequestParam(name = "location") String location,
                                                  @RequestParam(name = "dateFrom") String dateFrom,
                                                  @RequestParam(name = "dateTill") String dateTo,
                                                  @RequestParam(name = "priceFrom") String priceFrom,
                                                  @RequestParam(name = "priceTill") String priceTo) {
        try {
            Double tmpFrom = Double.parseDouble(priceFrom);
            Double tmpTo = Double.parseDouble(priceTo);
            String response = carService.findByLocationDatePrice(location, dateFrom, dateTo, tmpFrom, tmpTo);
            if (response != null) {
                return ResponseEntity.status(200).body(response);
            } else {
                return ResponseEntity.status(400).body("There is no cars by selected parameters");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + ex.getMessage());
        }
    }

    /**
     * A method that intercepts a request from url "/addcar" and passes it to carService
     *
     * @param data
     * @return ResponseEntity with code 201 and body with message "Car successfully added" if car is added
     * or
     * ResponseEntity with code 400 if bad request
     * or
     * ResponseEntity with code 401 if user dont have a valid token
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @PostMapping("/addcar")
    public ResponseEntity addCar(@RequestBody String data) {
        try {
            if (carService.addCar(data)) {
            //TODO after refactor need to add here filterService.addFilter(FullCarDto)
                return ResponseEntity.status(201).body("Car successfully added");
            } else {
                return ResponseEntity.status(400).body("Bad request");
            }
            //TODO in future we need to create ResponseEntity with status 401 Unauthorized
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + e.getMessage());
        }
    }

    /**
     * A method that intercepts a request from url "/rentcar" and passes it to carService
     *
     * @param data
     * @return ResponseEntity with code 200 and body with message "Car successfully booked" if car valid
     * or
     * ResponseEntity with code 400 if bad request
     * or
     * ResponseEntity with code 401 if user dont have a valid token
     * or
     * ResponseEntity with code 409 if date is busy
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @PostMapping("/rentcar")
    public ResponseEntity rentCar(@RequestBody String data) {
        //TODO need to create ExceptionHandler
        try {
            if (checkIfAvailable(data).getStatusCode() == HttpStatus.CONFLICT) {
                return ResponseEntity.status(409).body("Current date is busy");
            }
            if (carService.rentCar(data)) {
                return ResponseEntity.status(200).body("Car successfully booked");
            } else {
                return ResponseEntity.status(400).body("Bad request");
            }
            //TODO in future we need to create ResponseEntity with status 401 Unauthorized
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Parse exception. Message:" + e.getMessage());
        }
    }

    /**
     * A method that intercepts a request from url "car/return/{id}" where
     * {id} is id of returned car
     * and passes it to carService
     *
     * @param carId
     * @return ResponseEntity with code 200 and body with message "Car successfully returned" if all right
     * or
     * ResponseEntity with code 500 if we have exception from server with exception message in body
     */
    @Override
    @PostMapping("car/return/{id}")
    public ResponseEntity returnCar(@PathVariable("id") String carId) {
        try {
            UUID tmp = UUID.fromString(carId);
            if (carService.returnCar(tmp)) {
                return ResponseEntity.status(200).body("Car with id: " + carId + " successfully returned");
            } else {
                return ResponseEntity.status(404).body("Car with id: " + carId + " not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Something was wrong. Message: " + ex.getMessage());
        }

    }

    /**
     * Local method without url for checking available dates
     *
     * @param data
     */
    @Override
    public ResponseEntity checkIfAvailable(String data) {
        if (carService.checkIfAvailable(data)) {
            return ResponseEntity.status(200).body("OK");
        } else {
            return ResponseEntity.status(409).body("Conflict");
        }
    }
}
