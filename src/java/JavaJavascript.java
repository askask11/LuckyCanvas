/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jianqing
 */
import javax.script.*;

public class JavaJavascript {

    public static void main(String args[]) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        
        Object eval = engine.eval("var request = new XMLHttpRequest(); ");
        System.out.println(eval);

    }
}
