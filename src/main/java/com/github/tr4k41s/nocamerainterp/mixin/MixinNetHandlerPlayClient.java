package com.github.tr4k41s.nocamerainterp.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handlePlayerPosLook", at = @At("RETURN"))
    private void afterHandlePlayerPosLook(S08PacketPlayerPosLook packetIn, CallbackInfo ci) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player != null) {
            player.prevPosX = player.posX;
            player.prevPosY = player.posY;
            player.prevPosZ = player.posZ;
            player.prevRotationYaw = player.rotationYaw;
            player.prevRotationPitch = player.rotationPitch;
            player.lastTickPosX = player.posX;
            player.lastTickPosY = player.posY;
            player.lastTickPosZ = player.posZ;
        }
    }
}