package com.rbdavis.java.spell;

import java.util.SortedSet;

public class Words implements ITrie
{
    private WordNode root;
    private int nodeCount;
    private int wordCount;
    private SortedSet<String> prevWords;

    public Words()
    {
        this.root = null;
        nodeCount = 0;
        wordCount = 0;
        prevWords = null;
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
        return this.wordCount;
    }

    public int getNodeCount()
    {
        return this.nodeCount;
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



    public class WordNode implements ITrie.INode
    {
        int frequency;
        String substr;
        WordNode[] letters = new WordNode[26];

        public int getValue()
        {
            return this.frequency;
        }
    }

}
