        package com.drew.metadata;

        import com.drew.imaging.ImageMetadataReader;
        import com.drew.imaging.ImageProcessingException;
        import com.drew.imaging.jpeg.JpegMetadataReader;
        import com.drew.imaging.jpeg.JpegProcessingException;
        import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
        import com.drew.lang.GeoLocation;
        import com.drew.metadata.exif.ExifReader;
        import com.drew.metadata.exif.GpsDirectory;
        import com.drew.metadata.iptc.IptcReader;

        import javax.print.attribute.DateTimeSyntax;
        import java.io.File;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.Collection;
        import java.util.Scanner;
        import java.util.regex.*;

        /**
 * Showcases the most popular ways of using the metadata-extractor library.
 * <p>
 * For more information, see the project wiki: https://github.com/drewnoakes/metadata-extractor/wiki/GettingStarted
 *
 * @author Drew Noakes https://drewnoakes.com
 */
public class Main
{
    public static void main(String[] args)
    {
        File osmFilePath = new File("C:\\Users\\andym\\OneDrive\\Desktop\\Denver-FrontrangeMaps\\Golden.osm"); //use filepath for .osm file
        String imageFilePath = "C:\\Users\\andym\\OneDrive\\Pictures\\Camera Roll\\GoldenMapPhotos"; //filepath of folder with 1000 photos
        String osmTaggedFilePath = "C:\\Users\\andym\\OneDrive\\Desktop\\Denver-FrontrangeMaps";
        String dataToCPPFilePath = "C:\\Users\\andym\\CLionProjects\\Rtree\\cmake-build-debug";
        int sizeOfGallery = 1000; //how many photos you want in your gallery. Must be less than or equal to number of files in folder.

        photoGallery gallery = new photoGallery(imageFilePath, osmFilePath, sizeOfGallery);
        gallery.makePhotoGallery(); //compile array of photo objects, that have photo and metadata
        gallery.WriteTags(osmTaggedFilePath);
        gallery.WriteData(dataToCPPFilePath);




    }
}
