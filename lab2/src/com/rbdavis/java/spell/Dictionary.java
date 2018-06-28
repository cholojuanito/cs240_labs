package com.rbdavis.java.spell;

import java.util.Objects;

public class Dictionary implements ITrie
{
    private WordNode root;
    private int nodeCount;
    private int wordCount;
    private final char[] AtoZ = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    /* The ASCII values of ['a'-'z'] are 97 - 123. Subtracting 97 will put the values between 0-25
     * One index for each letter of the alphabet.
     */
    private final char NINETY_SEVEN = 'a';

    public Dictionary()
    {
        this.root = new WordNode();
        nodeCount = 1;
        wordCount = 0;
    }
    public void add(String word)
    {
        int wordSize = word.length();
        int lastIndex = word.length() - 1;
        WordNode n = this.root;
        for (int i = 0; i < wordSize; i++) {
            int letterIndex = word.charAt(i) - NINETY_SEVEN;
            if (n.children[letterIndex] == null)
            {
                String wordSubStr = word.substring(0, i + 1);
                this.addAWordNode(n, letterIndex, wordSubStr);
                if (i == lastIndex)
                {
                    wordCount++;
                    n.children[letterIndex].incrementFrequency();
                }
                n = nextNode(n, letterIndex);
            }
            else
            {
                if (i == lastIndex)
                {
                    // This word is still a new word.
                    if(n.children[letterIndex].getValue() < 1)
                    {
                        wordCount++;
                    }
                    n.children[letterIndex].incrementFrequency();
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
        n.children[letterIndex] = new WordNode();
        n.children[letterIndex].setSubStr(subStr);
        nodeCount++;
    }

    private WordNode nextNode(WordNode n, int letterIndex)
    {
        return n.children[letterIndex];
    }

    public WordNode find(String word)
    {
        int wordSize = word.length();
        int lastIndex = word.length() - 1;

        WordNode n = this.root;
        for (int i = 0; i < wordSize; i++)
        {
            int letterIndex = word.charAt(i) - NINETY_SEVEN;
            if (n.children[letterIndex] != null)
            {
                if (i == lastIndex)
                {
                    if(n.children[letterIndex].getValue() > 0)
                    {
                        return n.children[letterIndex];
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
        return this.wordCount;
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
        sb.append(this.wordCount);
        sb.append("\nWORDS:\n");
        this.toStringWords(this.root, sb);
        return sb.toString();
    }

    private void toStringWords(WordNode n, StringBuilder sb)
    {
        for (char c : this.AtoZ)
        {
            int letterIndex = c - NINETY_SEVEN;
            if(n.children[letterIndex] != null)
            {
                if(n.children[letterIndex].getValue() > 0)
                {
                    sb.append(n.children[letterIndex].getSubStr());
                    sb.append(" ");
                    sb.append(n.children[letterIndex].getValue());
                    sb.append("\n");
                }
                this.toStringWords(this.nextNode(n, letterIndex), sb);
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        if (this.getClass() != o.getClass())
        {
            return false;
        }
        Dictionary other = (Dictionary) o;
        return this.nodeCount == other.nodeCount &&
                Objects.equals(this.root, other.root) &&
                Objects.equals(this.wordCount, other.wordCount);
    }

    @Override
    public int hashCode()
    {
        // TODO: ask if this is okay!
        return Objects.hash(this.root, this.wordCount, this.nodeCount);
    }

    private boolean equalTrieTrees(Dictionary other)
    {
        return false;
    }


    public class WordNode implements ITrie.INode
    {
        int frequency;
        String subStr;
        WordNode[] children = new WordNode[26];

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
