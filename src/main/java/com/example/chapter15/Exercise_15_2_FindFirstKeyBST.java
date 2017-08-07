package com.example.chapter15;

/**
 * Write a program that takes as input a BST and a value, and returns
 * the first key that would appear in an inorder traversal which is
 * greater than the input value.
 */
public class Exercise_15_2_FindFirstKeyBST {
    public static Node root = buildSampleBST();

    private static Node buildSampleBST() {
        Node D = new Node(null, null);
        Node E = new Node(null, null);
        Node H = new Node(null, null);
        Node G = new Node(H, null);
        Node C = new Node(D, E);
        Node F = new Node(null, G);
        Node B = new Node(C, F);
        return B;
    }

}

class Node {
    Node left;
    Node right;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}

