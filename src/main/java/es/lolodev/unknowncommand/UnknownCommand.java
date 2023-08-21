 package es.lolodev.unknowncommand;

 import org.bukkit.Bukkit;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerCommandPreprocessEvent;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;

 public final class UnknownCommand extends JavaPlugin implements Listener

 {
   public void onEnable() {
     getServer().getPluginManager().registerEvents(this, (Plugin)this);
     config();
     Bukkit.getConsoleSender().sendMessage("§7[§cUnknownCommand§7] §aUnknown command is enabled!");
   }



   public void onDisable() {
     Bukkit.getConsoleSender().sendMessage("§7[§cUnknownCommand§7] §cUnknownCommand is disabled :(");
  }


@EventHandler
   public void onCommand(PlayerCommandPreprocessEvent event) {
     Player player = event.getPlayer();

     String message = event.getMessage();
     String[] args = message.split(" ");
     String mld1 = getConfig().getString("messages.message1");
     String mld2 = getConfig().getString("messages.message2");
     boolean show = getConfig().getBoolean("command.show");

     if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
       event.setCancelled(true);

       if (mld1 == null || mld2 == null) {

         Bukkit.getConsoleSender().sendMessage("§7[§cUnknownCommand§7] &fError in config.yml");
       Bukkit.getConsoleSender().sendMessage("§cPlease, check your Config.yml file");

        return;
      }
      if (show) {

         player.sendMessage(mld1 + args[0] + mld2);
      }
      else {

         player.sendMessage(mld1 + mld2);
       }
     }
}


   public void config() {
     reloadConfig();

     getConfig().addDefault("messages.message1", "§c§lHey! §7The command §c");
     getConfig().addDefault("messages.message2", " §7doesn't exist!");
     getConfig().addDefault("command.show", Boolean.valueOf(true));
     getConfig().options().copyDefaults(true);
     saveConfig();
   }
 }


