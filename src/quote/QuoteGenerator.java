package quote;

import arc.*;
//import arc.flabel.*;
import arc.util.*;
import arc.struct.Seq;
import mindustry.*;
import mindustry.graphics.Pal;
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
            int index = 0;
            messages = Seq.with("become funny", "seriously", "πed");
            
            // show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Quote Gen");
                dialog.cont.labelWrap(() -> messages.get(index)).update(l -> l.setText(messages.get(index))).size(1000f, 100f).padBottom(8f).row();
                dialog.cont.image(Core.atlas.find("whiteui")).color(Pal.gray).size(1000f, 3.50f).padTop(96f).row();
                
                dialog.cont.table(Styles.none, t -> {
                   t.button("Reroll", () -> {
                       messages.shuffle();
                       Log.info("Rerolled some messages.");
                   }).size(100f, 50f).margin(4f);
                   t.button("Close", dialog::hide).size(100f, 50f).margin(4f).padLeft(4f);
                }).size(100f, 50f).margin(4f).padLeft(4f).center();
                
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some/none example content.");
    }

}
