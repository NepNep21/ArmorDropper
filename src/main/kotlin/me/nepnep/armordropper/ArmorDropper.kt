package me.nepnep.armordropper

import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.TickTimer
import com.lambda.client.util.TimeUnit
import com.lambda.client.util.items.armorSlots
import com.lambda.client.util.items.throwAllInSlot
import com.lambda.client.util.threads.safeListener
import net.minecraftforge.fml.common.gameevent.TickEvent

object ArmorDropper : PluginModule(
    name = "ArmorDropper",
    category = Category.PLAYER,
    description = "Drops your armor",
    pluginMain = ArmorDropperPlugin
) {
    private val delay by setting("Tick Delay", 1, 0..20, 1)

    private val timer = TickTimer(TimeUnit.TICKS)

    init {
        safeListener<TickEvent.ClientTickEvent> {
            if (it.phase != TickEvent.Phase.START || !timer.tick(delay)) {
                return@safeListener
            }

            for (slot in player.armorSlots) {
                if (!slot.stack.isEmpty) {
                    throwAllInSlot(slot)
                }
            }
        }
    }
}