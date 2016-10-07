package AnnounceClear;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent ;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerAchievementAwardedEvent;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.Player;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.lang.TextContainer;
import java.util.ArrayList;

public class Main extends PluginBase implements Listener
{
	@Override
    public void onLoad () {
		this.getLogger().info(TextFormat.GREEN + "AnnounceClear 로딩완료");
	}
	@Override
    public void onEnable () {
		
        this.getServer().getPluginManager().registerEvents(this,
		this);
	}
	@EventHandler (priority=EventPriority.NORMAL)       
		  public void onPlayerJoinEvent (PlayerJoinEvent ev){
		String name = ev.getPlayer ().getName ();   
			  String message = String.valueOf (ev.getJoinMessage ());
			  if (ev.getJoinMessage().toString().trim().length() > 0) {
			
			this.BroadCastPopUp(this.getServer ().getLanguage ().translateString (message,
			new String []{
				name
			}));          
				  ev.setJoinMessage(String.valueOf(""));
		}
	}
	@EventHandler (priority=EventPriority.NORMAL)
	    public void onPlayerDeathEvent (PlayerDeathEvent ev){
		String name = ev.getEntity().getName ();   	
			  String message = String.valueOf (ev.getDeathMessage ());
		if (ev.getDeathMessage().toString().trim().length() > 0) {
		
			this.BroadCastPopUp(this.getServer ().getLanguage ().translateString (message,
			new String []{
				name
			}));          
			ev.setDeathMessage(String.valueOf(""));
		}
	}
	@EventHandler (priority=EventPriority.NORMAL)
	public void PlayerQuitEvent (PlayerQuitEvent ev){
		String name = ev.getPlayer ().getName ();   	
		String message = String.valueOf (ev.getQuitMessage ());
		if (ev.getQuitMessage().toString().trim().length() > 0) {
			
			this.BroadCastPopUp(this.getServer ().getLanguage ().translateString (message,
			new String []{
				name
			}));          
			ev.setQuitMessage(String.valueOf(""));
		}
	}
	
	public void BroadCastPopUp (String str){
		for (Player p : new ArrayList<>(this.getServer().getOnlinePlayers().values())) {
			p.sendPopup(str);
		}
	}
}