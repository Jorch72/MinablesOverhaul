package teamasm.moh.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import teamasm.moh.reference.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon3055 on 4/08/2016.
 * This class will contain a bunch of useful methods for rendering and gui elements as well as other gui related things.
 */
public class GuiUtils {
    public static ResourceLocation guiElements = new ResourceLocation(Reference.MOD_PREFIX + "textures/gui/guiElements.png");

//    public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
//        double zLevel = 300;
//        Tessellator tessellator = Tessellator.getInstance();
//        VertexBuffer vertexbuffer = tessellator.getBuffer();
//        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
//        vertexbuffer.pos((double)(x + 0), (double)(y + height), zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).endVertex();
//        vertexbuffer.pos((double)(x + width), (double)(y + height), zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).endVertex();
//        vertexbuffer.pos((double)(x + width), (double)(y + 0), zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
//        vertexbuffer.pos((double)(x + 0), (double)(y + 0), zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
//        tessellator.draw();
//    }

    public static void drawDefaultBackground(GuiScreen gui, int x, int y, int width, int height) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiElements);
        gui.drawTexturedModalRect(x, y, 0, 0, width / 2, height / 2);
        gui.drawTexturedModalRect(x + width / 2, y, 150 - width / 2, 0, width / 2, height / 2);
        gui.drawTexturedModalRect(x, y + height / 2, 0, 150 - height / 2, width / 2, height / 2);
        gui.drawTexturedModalRect(x + width / 2, y + height / 2, 150 - width / 2, 150 - height / 2, width / 2, height / 2);
    }

    public static void drawEnergyBar(GuiScreen gui, int x, int y, int height, int energyStored, int maxEnergyStored, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiElements);

        gui.drawTexturedModalRect(x, y, 0, 150, 14, height);
        gui.drawTexturedModalRect(x, y + height - 1, 0, 255, 14, 1);
        int draw = (int) ((double) energyStored / (double) maxEnergyStored * (height - 2));
        gui.drawTexturedModalRect(x + 1, y + height - draw - 1, 14, height + 150 - draw, 12, draw);

        if (isInRect(x, y, 14, height, mouseX, mouseY)) {
            List<String> list = new ArrayList<String>();
            list.add(energyStored + " / " + maxEnergyStored + " RF");
            net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
            GlStateManager.disableLighting();
        }
    }

    public static void drawPlayerSlots(GuiScreen gui, int posX, int posY, boolean center) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiElements);

        if (center) {
            posX -= 81;
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                gui.drawTexturedModalRect(posX + x * 18, posY + y * 18, 150, 0, 18, 18);
            }
        }

        for (int x = 0; x < 9; x++) {
            gui.drawTexturedModalRect(posX + x * 18, posY + 58, 150, 0, 18, 18);
        }
    }

    public static void drawSlot(GuiScreen gui, int posX, int posY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiElements);
        gui.drawTexturedModalRect(posX, posY, 150, 0, 18, 18);
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    public static void drawString(GuiScreen gui, String string, int x, int y) {
        gui.mc.fontRendererObj.drawString(string, x, y, 16777215);
    }
}
