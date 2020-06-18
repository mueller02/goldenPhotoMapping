package com.drew.metadata;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class photoGallery {
    photo[] gallery;
    int sizeOfGallery;
    String pictureFilePath;
    File osmFilePath;


    public photoGallery(String pics, File osm, int size){
        pictureFilePath = pics;
        osmFilePath = osm;
        sizeOfGallery = size;
        gallery = new photo[size];
    }

    public void makePhotoGallery(){
        try {
            Scanner scanner = new Scanner(osmFilePath);
            int numOfNodes = 16619;                     //Number of nodes in .osm file
            String[] allNodes = new String[numOfNodes];     //An array the exact size as the number of nodes we have
            int nodeIndex = 0;

            while(scanner.hasNext()) {
                String line = scanner.nextLine();

                if(Pattern.matches("\\s <node id=.*", line)){
                    allNodes[nodeIndex] = line;
                    nodeIndex++;
                    //System.out.println(nodeIndex + line); //Uncomment this line and comment out two lines above to see how many nodes are in your.osm file
                }

            }
            scanner.close();


            for(int i=0; i < sizeOfGallery; i++){
                String node = allNodes[(int)(Math.random()*numOfNodes)];
                photo image = new photo(i+1, pictureFilePath, node);
                gallery[i] = image;
                //System.out.println(image.toString());
            }

        } catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

}
