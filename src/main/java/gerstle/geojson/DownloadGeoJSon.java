package gerstle.geojson;

import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadGeoJSon
{
    public static void main(String[] args) throws IOException
    {
//        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
//        URLConnection connection = url.openConnection();
//        InputStream inputStream = connection.getInputStream();
//        Gson gson = new Gson();
//
//        InputStreamReader reader = new InputStreamReader(inputStream);
//        GeoJSonFeed feed = gson.fromJson(reader, GeoJSonFeed.class);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GeoJSonService service = retrofit.create(GeoJSonService.class);

        //Do not use blocking get;
        GeoJSonFeed feed = service.getSignificantEarthquakes()
                .blockingGet();

        Feature largest = feed.features.get(0);
        for(Feature feature: feed.features)
        {
            if(feature.properties.mag>largest.properties.mag)
            {
                largest=feature;
            }
        }
        System.out.printf("%s %f %d",
                largest.properties.place,
                largest.properties.mag,
                largest.properties.time);
        System.exit(0);
    }
}
