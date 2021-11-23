package quote;

import arc.*;
import arc.flabel.*;
import arc.util.*;
import arc.struct.Seq;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.*; // ~~is this necessary?~~
import mindustry.ui.dialogs.*;

public class QuoteGenerator extends Mod{
    public Seq<String> messages = new Seq<String>();
    
    public QuoteGenerator(){
        Log.info("Loaded Quote Generator constructor.");

        // listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            messages = Seq.with("become funny", "seriously", "πed");
            // show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("");
                dialog.cont.label(() -> "").update(l -> l.setText(messages.random())).row();
                dialog.cont.button("Reroll", messages.shuffle()).margin(4);
                dialog.cont.button("Close", dialog::hide).margin(4).padLeft(4);
                
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some/none example content.");
    }

}
