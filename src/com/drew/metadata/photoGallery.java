package com.drew.metadata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class photoGallery {
    photo[] gallery;
    int sizeOfGallery;
    String pictureFilePath;
    File osmFilePath;
    ArrayList<Node> allNodes = new ArrayList<>(); //stores all nodes from .osm
    ArrayList<String> allData = new ArrayList<>(); //stores all lines from .osm file


    public photoGallery(String pics, File osm, int size){
        pictureFilePath = pics;
        osmFilePath = osm;
        sizeOfGallery = size;
        gallery = new photo[size];
    }

    public void makePhotoGallery(){
        try {
            Scanner scanner = new Scanner(osmFilePath);
            int nodeIndex = 0;

            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                allData.add(line);
                nodeIndex++;

                if(Pattern.matches("\\s <node id=.*", line)){
                    Node tempNode = new Node(line, nodeIndex);
                    allNodes.add(tempNode);
                    allData.add(line);
                }

            }
            scanner.close();


            for(int i=0; i < sizeOfGallery; i++){
                Node node = allNodes.get((int)(Math.random()*allNodes.size()));
                photo image = new photo(i+1, pictureFilePath, node);
                gallery[i] = image;
                System.out.println(image.toString());

            }

        } catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void WriteTags(String FilePath) {
        for(int i = 0; i < sizeOfGallery; i++){
            int indexGuess = gallery[i].theNode.index;

            while(true){
                if(allData.get(indexGuess) == gallery[i].theNode.Nodetxt){
                    allData.add(indexGuess+1, "    <tag k='image' v='"  + gallery[i].photo_id +  ".jpg' />");
                    if(allData.get(indexGuess + 2).contains("<node id=")){
                        allData.add(indexGuess+2,"  </node>");
                    }
                    break;
                }
                indexGuess++;
            }

        }

        try {
            FilePath = FilePath + "//GoldenMapTagged.osm";
            File output = new File(FilePath);
            FileWriter writer = new FileWriter(output);

            for (String data : allData) {
                writer.write(data + "\n");
            }

        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

}
