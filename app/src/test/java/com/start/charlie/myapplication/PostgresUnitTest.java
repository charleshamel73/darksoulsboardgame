package com.start.charlie.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.start.charlie.myapplication.PostgresUtil;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PostgresUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        PostgresUtil util = new PostgresUtil();
        util.connectToServer("bullshit");


        util.addCharacter("charlieq","Thief",2);

        util.addCharacter("charliewdw","Thief",1);
        util.getListOfCharacters();
//        util.addClass("Thief","Stealing always",7);
//        assertTrue(false);
    }


}