package com.rbdavis.java.spell;

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
                this.addAWordNode(n, letterIndex);
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

    private void addAWordNode(WordNode n, int letterIndex)
    {
        n.children[letterIndex] = new WordNode();
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

    public char[] getAtoZ() {
        return this.AtoZ;
    }

    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        StringBuilder currWord = new StringBuilder();
        output.append("NUM NODES: ");
        output.append(this.nodeCount);
        output.append(" NUM WORDS: ");
        output.append(this.wordCount);
        output.append("\nWORDS:\n");
        this.toStringWords(this.root, output, currWord);
        return output.toString();
    }

    private void toStringWords(WordNode n, StringBuilder output, StringBuilder currWord)
    {
        for (char c : this.AtoZ)
        {
            int letterIndex = c - NINETY_SEVEN;
            if(n.children[letterIndex] != null)
            {
                currWord.append(c);
                if(n.children[letterIndex].getValue() > 0)
                {
                    output.append(currWord.toString());
                    output.append(" ");
                    output.append(n.children[letterIndex].getValue()).append("\n");
                }
                this.toStringWords(this.nextNode(n, letterIndex), output, currWord);
            }
        }

        if(n != this.root)
        {
            int lastIndex = currWord.toString().length() - 1;
            currWord.deleteCharAt(lastIndex);
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
        if (this.wordCount != other.wordCount && this.nodeCount != other.nodeCount)
        {
            return false;
        }
        return  this.equalTrieTrees(this.root, other.root);
    }

    private boolean equalTrieTrees(WordNode n1, WordNode n2)
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 31 * this.nodeCount * this.wordCount;
    }


    public class WordNode implements ITrie.INode
    {
        int frequency;
        WordNode[] children = new WordNode[26];

        public int getValue()
        {
            return this.frequency;
        }

        public void incrementFrequency()
        {
            this.frequency++;
        }
    }

}
