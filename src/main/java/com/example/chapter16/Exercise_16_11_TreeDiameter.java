package com.example.chapter16;

import java.util.*;

class Exercise_16_11_TreeDiameter {
  class Node {
    List<Edge> childs;
  }

  class Edge {
    int length;
    Node child;
  }

  static class HeightAndDiameter {
    int height;
    int diameter;

    public HeightAndDiameter(int height, int diameter) {
      this.height=height;
      this.diameter=diameter;
    }
  }

  public static int treeDiameter(Node root){
    return diameterHelper(root).diameter;
  }

  public static HeightAndDiameter diameterHelper(Node node) {
    int maxHeightOne = 0;
    int maxHeightTwo = 0;
    int maxDiameter = 0;
    for (Edge e : node.childs) {
      HeightAndDiameter hd = diameterHelper(e.child);
      if (hd.height + e.length > maxHeightTwo) {
        if (hd.height + e.length > maxHeightOne) {
          maxHeightTwo = maxHeightOne;
          maxHeightOne = hd.height + e.length;
        } else {
          maxHeightTwo = hd.height + e.length;
        }
      }
      maxDiameter = Math.max(maxDiameter, hd.diameter);
    }
    // Use maximum height among all children
    int height = maxHeightOne;

    // Check if current diameter is larger than the max diameter found
    // of every subtree and return
    int diameter = Math.max(maxHeightOne + maxHeightTwo, maxDiameter);
    return new HeightAndDiameter(height, diameter);
  }
}

