package me.nepnep.armordropper

import com.lambda.client.plugin.api.Plugin

object ArmorDropperPlugin : Plugin() {
    override fun onLoad() {
        modules.add(ArmorDropper)
    }
}