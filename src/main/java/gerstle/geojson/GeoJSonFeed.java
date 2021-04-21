package gerstle.geojson;

import java.util.List;

public class GeoJSonFeed
{
    List<Feature> features;

}

class Feature{
    FeatureProperties properties;
    Geometry geometry;

}

class FeatureProperties
{
    double mag;
    String place;
    long time;

}
class Geometry
{
    String type;
    List<Double> coordinates;
}