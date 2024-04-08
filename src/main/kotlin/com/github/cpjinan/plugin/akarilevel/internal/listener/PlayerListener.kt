package com.github.cpjinan.plugin.akarilevel.internal.listener

import com.github.cpjinan.plugin.akarilevel.api.AkariLevelAPI
import com.github.cpjinan.plugin.akarilevel.internal.database.type.PlayerData
import com.github.cpjinan.plugin.akarilevel.internal.manager.ConfigManager
import com.github.cpjinan.plugin.akarilevel.internal.manager.DatabaseManager
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent
import kotlin.math.roundToInt

object PlayerListener {
    @SubscribeEvent
    fun onPlayerExpChange(event: PlayerExpChangeEvent) {
        val exp: Double = event.amount * ConfigManager.settings.getDouble("Level.Vanilla-Exp-Rate")
        AkariLevelAPI.addPlayerExp(event.player, exp.roundToInt(), "VANILLA_EXP_CHANGE")
        AkariLevelAPI.refreshPlayerLevel(event.player, "VANILLA_EXP_CHANGE")
        event.amount = 0
    }

    @SubscribeEvent
    fun onPlayerJoin(event: PlayerJoinEvent) {
        AkariLevelAPI.refreshPlayerLevel(event.player, "LISTENER_PLAYER_JOIN")
    }

    @SubscribeEvent
    fun onPlayerQuit(event: PlayerQuitEvent) {
        DatabaseManager.getDatabase().save()
    }
}