package com.zy.jungletest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        assert false;
    }

    @Test
    public void java8Test() {
//        Enemy e = new Enemy(10,10,"12");
//        EnemySlime a = new EnemySlime();
//        a.setColor("red");
//        a.setHp(250);
//        EnemySlime b = new EnemySlime();
//        b.setColor("red");
//        b.setHp(100);
//        EnemySlime c = new EnemySlime();
//        c.setColor("red");
//        c.setHp(50);
//        Stream.of(a, b, c)
//                .filter(enemySlime -> enemySlime.getHp() > 80)
//                .forEach(enemySlime -> System.out.print("hp" + enemySlime.getHp() + ",att" + enemySlime.getAtt()));
    }

    @Test
    public void textString() {
        System.out.print( String.valueOf("2B".compareTo("A")));
    }
}