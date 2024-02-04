package com.github.cpjinan.plugin.akarilevel.internal.database

import com.github.cpjinan.plugin.akarilevel.internal.database.type.PlayerData
import com.github.cpjinan.plugin.akarilevel.internal.manager.ConfigManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import org.bukkit.Bukkit
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class DbCbor : Database {
    private val file: File
    private val playerData: HashMap<String, PlayerData>

    init {
        val parent = Bukkit.getPluginManager().getPlugin("AkariLevel")?.dataFolder ?: File(".")
        file = File(parent, ConfigManager.getCborSection().getString("file")!!)
        playerData = if (file.exists()) {
            val content = file.readBytes()
            if (content.isNotEmpty()) {
                Cbor.decodeFromByteArray(content)
            } else {
                hashMapOf()
            }
        } else {
            hashMapOf()
        }
    }

    override fun getPlayerByName(name: String): PlayerData = playerData[name] ?: PlayerData()

    override fun updatePlayer(name: String, value: PlayerData) {
        playerData[name] = value
    }

    override fun save() {
        if (!file.exists()) file.createNewFile()

        file.writeBytes(Cbor.encodeToByteArray(playerData))
    }
}