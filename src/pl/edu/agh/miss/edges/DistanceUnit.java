package pl.edu.agh.miss.edges;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public enum DistanceUnit {

    METRES,
    KILOMETRES,
    MILES;

    private static double KILOMETER_TO_MILE = 0.621371;
    private static double MILE_TO_KILOMETER = 1.609344;

    public static double convertKilometresToMiles(double kilometers) {
        return kilometers * KILOMETER_TO_MILE;
    }

    public static double convertMilesToKilometers(double miles) {
        return miles * MILE_TO_KILOMETER;
    }

}
