package repository.inmem;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

public class GeoHashingDriver {

  public static void main(String[] args) {

    for (int precision = 1; precision < 13; precision++) {
      // Encode latitude and longitude
      GeoHash geoHash = GeoHash.withCharacterPrecision(37.7749, -122.4194, precision);
      System.out.println(geoHash.toBase32());

      // Decode geohash
      WGS84Point point = geoHash.getPoint();
      System.out.println(point.getLatitude() + ", " + point.getLongitude());
    }


  }
}
