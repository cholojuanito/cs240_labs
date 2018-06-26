package com.rbdavis.java.spell;

import java.util.SortedSet;
import java.util.TreeSet;

public class Dictionary implements ITrie
{
    private WordNode root;
    private SortedSet<String> words;
    private int nodeCount;

    /* The ASCII val of ['a'-'z'] is 97 - 123.
     * Subtracting 97 will put the index between 0-25
     * One index for each letter of the alphabet.
     */
    private final char NINETY_SEVEN = 'a';

    public Dictionary()
    {
        this.root = new WordNode();
        words = new TreeSet<>();
        nodeCount = 1;
    }

    /* The idea:
     *
     * 1. Go through each letter in the word to add.
     *      1. Check if the letter is present
     *          in the currWordNode.letters.
     *          No?
     *              1. Make a new WordNode (nodecount++) and note its substr
     *                  1. If you are at the last letter then update substr, wordCount++
     *                      and incrementFrequency.
     *              2. Set n = currWordNode
     *          Yes?
     *              1. If you are at the last letter then incrementFrequency.
     *              2. Else set n = currWordNode
     *
     */
    public void add(String word)
    {
        int wordSize = word.length();
        int lastIndex = word.length() - 1;
        words.add(word);
        WordNode n = this.root;
        for (int i = 0; i < wordSize; i++)
        {
            int letterIndex = word.charAt(i) - NINETY_SEVEN;
            if (n.letters[letterIndex] == null)
            {
                String wordSubStr = word.substring(0, i + 1);
                this.addAWordNode(n, letterIndex, wordSubStr);
                if (i == lastIndex)
                {
                    n.letters[letterIndex].incrementFrequency();
                }
                n = nextNode(n, letterIndex);
            }
            else
            {
                if (i == lastIndex)
                {
                    n.letters[letterIndex].incrementFrequency();
                }
                else
                {
                    n = nextNode(n, letterIndex);
                }
            }
        }
    }

    private void addAWordNode(WordNode n, int letterIndex, String subStr)
    {
        n.letters[letterIndex] = new WordNode();
        n.letters[letterIndex].setSubStr(subStr);
        nodeCount++;
    }

    private WordNode nextNode(WordNode n, int letterIndex)
    {
        return n.letters[letterIndex];
    }

    public WordNode find(String word)
    {
        int wordSize = word.length();
        int lastIndex = word.length() - 1;

        WordNode n = this.root;
        for (int i = 0; i < wordSize; i++)
        {
            int letterIndex = word.charAt(i) - NINETY_SEVEN;
            if (n.letters[letterIndex] != null)
            {
                if (i == lastIndex)
                {
                    if(n.letters[letterIndex].getValue() > 0)
                    {
                        return n.letters[letterIndex];
                    }
                    else
                    {
                        return null;
                    }
                }
                n = nextNode(n, letterIndex);
            }
        }
        return null;
    }

    public int getWordCount()
    {
        return this.words.size();
    }

    public int getNodeCount()
    {
        return this.nodeCount;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("NUM NODES: ");
        sb.append(this.nodeCount);
        sb.append(" NUM WORDS: ");
        sb.append(this.words.size());
        sb.append("\nWORDS:\n");

        for(String word : this.words)
        {
            //WordNode foundWord = find(word);
            //sb.append(word);
            //sb.append(foundWord.frequency);
            //sb.append("\n");
            sb.append(word);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        int primeNumber = 47;
        //TODO: Think of recursive way to do this.
        return 1;
    }

    @Override
    public boolean equals(Object other)
    {
        if(this == other)
        {
            return true;
        }
        if (!(other instanceof Dictionary))
        {
            return false;
        }
        Dictionary otherDictionary = (Dictionary) other;
      return false;
    }

    private boolean recursiveEquals(Object o)
    {
        return false;
    }


    public class WordNode implements ITrie.INode
    {
        int frequency;
        String subStr;
        WordNode[] letters = new WordNode[26];

        public int getValue()
        {
            return this.frequency;
        }

        public void incrementFrequency()
        {
            this.frequency++;
        }

        public void setSubStr(String subStr)
        {
            this.subStr = subStr;
        }

        public String getSubStr()
        {
            return this.subStr;
        }
    }

}
