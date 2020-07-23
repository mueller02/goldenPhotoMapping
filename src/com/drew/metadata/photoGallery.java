package com.drew.metadata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    public photoGallery(String pics, File osm, int size) {
        pictureFilePath = pics;
        osmFilePath = osm;
        sizeOfGallery = size;
        gallery = new photo[size];
    }

    public void makePhotoGallery() {
        try {
            Scanner scanner = new Scanner(osmFilePath);
            int nodeIndex = 0;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                allData.add(line);


                if (Pattern.matches("\\s <node id=.*", line)) {
                    Node tempNode = new Node(line, nodeIndex);
                    allNodes.add(tempNode);
                }
                nodeIndex++;

            }
            System.out.println(allData.get(allData.size() - 1));
            //System.out.println("aaa");
            scanner.close();

            Node node = allNodes.get((int) (Math.random() * allNodes.size()));
            LocalTime tm = LocalTime.of(0,5,0);

            photo image = new photo(1, pictureFilePath, node, tm);
            gallery[0] = image;

            for (int i = 1; i < sizeOfGallery; i++) {
                node = allNodes.get((int) (Math.random() * allNodes.size()));
                image = new photo(i + 1, pictureFilePath, node, gallery[i-1].theTime);
                gallery[i] = image;
                //System.out.println(image.toString());

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void WriteTags(String FilePath) {
        try {
            for (int i = 0; i < sizeOfGallery; i++) {
                int indexGuess = gallery[i].theNode.index;
                // System.out.println("1st: " + indexGuess);
                //System.out.println(gallery[i].nodeID);
                while (true) {
                    if (allData.get(indexGuess) == gallery[i].theNode.Nodetxt) {

                        ;

                        if (allData.get(indexGuess).contains("<node id=")) {

                            allData.add(indexGuess, "  <node id='" + gallery[i].nodeID + "' visible='true' version='4' lat='" + gallery[i].Latitude + "' lon='" + gallery[i].Longitude + "'>\n" + "    <tag k='image' v='" + gallery[i].photo_id + ".jpg' />" + "\n  </node>");
                        } else {
                            allData.add(indexGuess + 1, "    <tag k='image' v='" + gallery[i].photo_id + ".jpg' />");
                        }
                        break;
                    }
                    indexGuess++;
                }

            }


            FilePath = FilePath + "//GoldenMapTagged.osm";
            File output = new File(FilePath);
            FileWriter writer = new FileWriter(output);

            for (String data : allData) {
                writer.write(data + "\n");
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void WriteData(String filePath){
        try {
            filePath += "//GoldenData.txt";
            File data = new File(filePath);
            FileWriter writer = new FileWriter(data);

            for (photo picture : gallery) {
                writer.write(picture.photo_id + "\n");
                writer.write(picture.photo_filepath + "\n");
                writer.write(picture.outside + "\n");
                writer.write(picture.nodeID + "\n");
                writer.write(picture.Longitude + "\n");
                writer.write(picture.Latitude + "\n");
                writer.write(picture.theDate.getYear() + " " + picture.theDate.getMonthValue() + " "+ picture.theDate.getDayOfMonth() + "\n");
                writer.write(picture.theTime.getHour() + " " + picture.theTime.getMinute() + " " + picture.theTime.getSecond() + "\n");
                writer.write("\n");
                System.out.println(picture.toString());
            }
            writer.close();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
}
