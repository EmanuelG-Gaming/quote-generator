package example;

import arc.*;
import arc.flabel.*;
import arc.util.*;
import arc.struct.Seq.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ExampleJavaMod extends Mod{
    public Seq<String> messages = new Seq<String>();
    
    public ExampleJavaMod(){
        Log.info("Loaded Quote Generator constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            messages.add("become funny", "seriously");
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("");
                dialog.cont.add(messages.random()).row();
                dialog.cont.button("Hide", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
    }

}
