package com.hackathon.inditex.util;

/**
 * Utility class for Geo calculations
 */
public class GeoUtil {

    private static final int EARTH_RADIUS = 6371;

    /**
     * Calculate the distance between two points in latitude and longitude
     *
     * @param lat1 Latitude of the first point
     * @param lon1 Longitude of the first point
     * @param lat2 Latitude of the second point
     * @param lon2 Longitude of the second point
     * @return The distance between the two points in kilometers
     */
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // in km
        return EARTH_RADIUS * c;
    }
}
