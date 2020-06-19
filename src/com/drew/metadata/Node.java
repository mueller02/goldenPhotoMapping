package com.drew.metadata;

public class Node {
    int index;
    String Nodetxt;

    public Node(String node, int i){
        Nodetxt = node;
        index = i;
    }

    public void setNodetxt(String n){
        Nodetxt = n;
    }
}
