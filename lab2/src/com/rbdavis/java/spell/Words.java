package com.rbdavis.java.spell;

import java.io.File;
import java.util.SortedSet;

public class Words implements ITrie
{
    private File file;
    private Node root;
    private int nodeCount;
    private int wordCount;
    private SortedSet<String> prevWords;

    public Words()
    {

    }
    public Words(File file)
    {
        this.file = file;
    }

    public void add(String word)
    {

    }

    public ITrie.INode find(String word)
    {
         return new INode() {
             @Override
             public int getValue() {
                 return 0;
             }
         };
    }

    public int getWordCount()
    {
        return 1;
    }

    public int getNodeCount()
    {
        return 1;
    }

    @Override
    public String toString()
    {
        return "this for now";
    }

    @Override
    public int hashCode()
    {
        return 1;
    }

    @Override
    public boolean equals(Object o)
    {
      return false;
    }



    public class Node implements ITrie.INode
    {
        int frequency;
        String substr;
        Node[] letters = new Node[26];

        public int getValue()
        {
            return this.frequency;
        }
    }

}
