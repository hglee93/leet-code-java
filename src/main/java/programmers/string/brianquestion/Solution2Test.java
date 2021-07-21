package programmers.string.brianquestion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution2Test {

    @Test
    void solution() {
        Solution2 sol = new Solution2();
        Assertions.assertEquals("A BBBB CC D D D", sol.solution("aAaBbBbBbBcCCcDdDdD"));
        Assertions.assertEquals("HELLO", sol.solution("aHELLOa"));
        Assertions.assertEquals("A A A A AA", sol.solution("AAAAxAbAx"));
        Assertions.assertEquals("HELLO", sol.solution("aHbEbLbLbOa"));
        Assertions.assertEquals("HELLO WORLD", sol.solution("HaEaLaLaObWORLDb"));
        Assertions.assertEquals("SIGONG J O A", sol.solution("SpIpGpOpNpGJqOqA"));
        Assertions.assertEquals("A", sol.solution("A"));
        Assertions.assertEquals("HELLO WORLD", sol.solution("aHbEbLbLbOacWdOdRdLdDc"));
        Assertions.assertEquals("HELL O WORLD", sol.solution("HaEaLaLObWORLDb"));
        Assertions.assertEquals("HELLOWORLD", sol.solution("aHELLOWORLDa"));
        Assertions.assertEquals("A A A B A BBBB C BBBB C BB GG G G G RRRRRR"
                , sol.solution("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR"));
        Assertions.assertEquals("I A M", sol.solution("aIaAM"));
        Assertions.assertEquals("AO", sol.solution("bAaOb"));
        Assertions.assertEquals("invalid", sol.solution("a"));
        Assertions.assertEquals("invalid", sol.solution("Aa"));
        Assertions.assertEquals("invalid", sol.solution("aA"));
        Assertions.assertEquals("invalid", sol.solution("HaEaLaLaOWaOaRaLaD"));
        Assertions.assertEquals("invalid", sol.solution("abHELLObaWORLD"));
        Assertions.assertEquals("invalid", sol.solution("aHELLOa bWORLDb"));
        Assertions.assertEquals("invalid", sol.solution("TxTxTxbAb"));
        Assertions.assertEquals("invalid", sol.solution("bTxTxTaTxTbkABaCDk"));
        Assertions.assertEquals("invalid", sol.solution("baHELLOabWORLD"));
        Assertions.assertEquals("invalid", sol.solution("A B"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("AxAxAxABcBcBcB"));
        Assertions.assertEquals("BB A", sol.solution("oBBoA"));
        Assertions.assertEquals("AAAA", sol.solution("AxAxAxA"));
        Assertions.assertEquals("HELLO WORLD SIGONG J O A GWFD GWL BB A A A AAAA A"
                , sol.solution("HaEaLaLaObWORLDbSpIpGpOpNpGJqOqAdGcWcFcDdeGfWfLeoBBoAAAAxAxAxAA"));
        Assertions.assertEquals("BA DE A E EEEE", sol.solution("aBcAadDeEdvAvlElmEEEEm"));
        Assertions.assertEquals("A A A B B B", sol.solution("AcAcABaBaB"));
        Assertions.assertEquals("GWFD GWL", sol.solution("aGbWbFbDakGnWnLk"));
        Assertions.assertEquals("X XX X", sol.solution("XcXbXcX"));
        Assertions.assertEquals("invalid", sol.solution("aCaCa"));
        Assertions.assertEquals("invalid", sol.solution("AxAxAxAoBoBoB"));
        Assertions.assertEquals("invalid", sol.solution("xAaAbAaAx"));
        Assertions.assertEquals("invalid", sol.solution("AsCsWsQsQsEEEEEEEEeEeEe"));
        Assertions.assertEquals("A B C D E F GH", sol.solution("ABCaDaEFGbH"));
        Assertions.assertEquals("A B B B AAA", sol.solution("aAaBBBcAeAeAc"));
        Assertions.assertEquals("A B C DEF H I", sol.solution("ABCbDaEaFbHI"));
        Assertions.assertEquals("invalid", sol.solution("AacacaA"));
        Assertions.assertEquals("invalid", sol.solution("AaBcBcBcBcB"));
        Assertions.assertEquals("invalid", sol.solution("aAAA"));
        Assertions.assertEquals("invalid", sol.solution("AAAa"));
        Assertions.assertEquals("invalid", sol.solution("aAbBBbAa"));
        Assertions.assertEquals("invalid", sol.solution("aAbBBbAa"));
        Assertions.assertEquals("invalid", sol.solution("aAAbBbAAa"));
        Assertions.assertEquals("invalid", sol.solution("aAcAbAbAcAcAcAa"));
        Assertions.assertEquals("invalid", sol.solution("acAcAcAa"));
        Assertions.assertEquals("invalid", sol.solution("aAcAcAca"));
        Assertions.assertEquals("A AAA A", sol.solution("AdAeAeAdA"));
        Assertions.assertEquals("invalid", sol.solution("dAAeAd"));
        Assertions.assertEquals("invalid", sol.solution("dAeAAd"));
        Assertions.assertEquals("ABA", sol.solution("cAbBbAc"));
        Assertions.assertEquals("invalid", sol.solution("AbbA"));
        Assertions.assertEquals("invalid", sol.solution("aAaaBa"));
        Assertions.assertEquals("A B", sol.solution("aAacBc"));
        Assertions.assertEquals("A B", sol.solution("AcBc"));
        Assertions.assertEquals("A B", sol.solution("aAaB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAbAbAbAacBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("AbAbAbABdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("AbAbAbAcBBBBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAbAbAbAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAbAbAbAacBBBBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAacBBBBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAacBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("AbAbAbAcBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", sol.solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("IM M M", sol.solution("IaMMbMb"));
        Assertions.assertEquals("invalid", sol.solution("AaAaAabBBb"));
        Assertions.assertEquals("A A AA", sol.solution("AaAaAcA"));
        Assertions.assertEquals("A B", sol.solution("aAabBb"));
        Assertions.assertEquals("B HEE", sol.solution("bBbcHdEdEc"));
        Assertions.assertEquals("AA A", sol.solution("AaAA"));
        Assertions.assertEquals("J OOO A", sol.solution("JaOOOaA"));
        Assertions.assertEquals("J O O O A", sol.solution("aJaOOOcAc"));
        Assertions.assertEquals("I AM", sol.solution("IaAMa"));
        Assertions.assertEquals("I A M", sol.solution("aIaAM"));
        Assertions.assertEquals("SIGONG J OOO A", sol.solution("SpIpGpOpNpGJqOOOqA"));
        Assertions.assertEquals("invalid", sol.solution("AxAxAxAoBoBoB"));
        Assertions.assertEquals("HELLO WORLD", sol.solution("HaEaLaLaOWbObRbLbD"));
        Assertions.assertEquals("AAAA B B B", sol.solution("AxAxAxABoBoB"));
        Assertions.assertEquals("B", sol.solution("aBa"));
        Assertions.assertEquals("invalid", sol.solution("baHELLOabWORLD"));
        Assertions.assertEquals("invalid", sol.solution("aAbAba"));
        Assertions.assertEquals("AO", sol.solution("bAaOb"));
        Assertions.assertEquals("A A A B B B BB", sol.solution("AAAaBaBBBbB"));
        Assertions.assertEquals("invalid", sol.solution("aa"));
        Assertions.assertEquals("invalid", sol.solution(" "));
        Assertions.assertEquals("X XX X", sol.solution("XcXbXcX"));
        Assertions.assertEquals("A A A B B B", sol.solution("AcAcABaBaB"));
        Assertions.assertEquals("invalid", sol.solution("Aaa"));
        Assertions.assertEquals("invalid", sol.solution("aaA"));
        Assertions.assertEquals("invalid", sol.solution("aaa"));
        Assertions.assertEquals("invalid", sol.solution("AbAaAbAaCa"));
        Assertions.assertEquals("X XX X", sol.solution("XcXbXcX"));
        Assertions.assertEquals("invalid", sol.solution("xAaAbAaAx"));
        Assertions.assertEquals("A A AA", sol.solution("AaAaAcA"));
        Assertions.assertEquals("HELLOWORLD", sol.solution("HELLOWORLD"));
        Assertions.assertEquals("invalid", sol.solution("TxTxTxbAb"));
        Assertions.assertEquals("invalid", sol.solution("bTxTxTaTxTbkABaCDk"));
        Assertions.assertEquals("invalid", sol.solution("xAaAbAaAx"));
        Assertions.assertEquals("invalid", sol.solution("Aa"));
    }

    @Test
    void ruleOne() {
        Solution2 sol = new Solution2();
        Assertions.assertEquals(false, sol.isRuleOne("AcAcABaBaB", 'c'));
        Assertions.assertEquals(true, sol.isRuleOne("AcA", 'c'));
        Assertions.assertEquals(false, sol.isRuleOne("AcAbA", 'c'));
        Assertions.assertEquals(false, sol.isRuleOne("AcAbAcA", 'c'));
        Assertions.assertEquals(false, sol.isRuleOne("AcAcAc", 'c'));
    }

    @Test
    void test() {
        Solution2 sol = new Solution2();
        Assertions.assertEquals("A BBBB CC D D D", sol.solution("aAaBbBbBbBcCCcDdDdD"));
    }

    @Test
    void test2() {
        Solution2 sol = new Solution2();
        Assertions.assertEquals("AAA BBB", sol.solution("AcAcABaBaB"));
    }
}