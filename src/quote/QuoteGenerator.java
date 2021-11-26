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

import static mindustry.Vars.*;

public class QuoteGenerator extends Mod{
    public Seq<String> messages = new Seq<String>();
    public int discoveredQuotes = 0;
    
    public QuoteGenerator(){
        Log.info("Loaded Quote Generator constructor.");
        
        // listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            int index = 0;
            
            // show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Quote Gen");
                dialog.cont.labelWrap(() -> "").update(l -> l.setText(messages.get(index))).size(1000f, 100f).padBottom(8f).row();
                dialog.cont.image(Core.atlas.find("whiteui")).color(Pal.gray).size(1000f, 3.50f).padTop(96f).row();
                
                dialog.cont.table(Styles.none, t -> {
                   t.button("Reroll", () -> {
                       messages.shuffle();
                       if (discoveredQuotes <= messages.size) discoveredQuotes++;
                       Log.info("Rerolled some messages.");
                   }).size(100f, 50f).margin(4f);
                   t.button("Close", dialog::hide).size(100f, 50f).margin(4f).padLeft(4f);
                   t.add("").update(l -> l.setText("Quotes: " + (discoveredQuotes >= messages.size ? "Every quote found" : discoveredQuotes) + " / " + messages.size)).padLeft(8f);
                }).size(100f, 50f).margin(4f).padLeft(4f).center();
                
                dialog.show();
            });
        });
    }
    @Override
    public void init(){
        if (!headless){
          messages = Seq.with(
            "Become funny.", 
            "Seriously.", 
            "πed",
            "If you belong to a vermin-like underclass of spoiled children, don't be spoiled, please.",
            "Focus on better things than worshipping the poorly-made quotes on the internet.",
            "Duh.",
            "Fun fact: you can Reroll this message to a random one. May also have the chance of landing on the same message.",
            "DoN't Be AfRaId, Antumbra will add more quotes."
          );
        }
    }
    /*
    @Override
    public void loadContent(){
        Log.info("Loading some/none example content.");
    }
    */

}
