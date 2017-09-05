package org.halvors.nuclearphysics.client.gui.modular.machine;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.client.gui.modular.GuiComponentContainer;
import org.halvors.nuclearphysics.client.utility.RenderUtility;
import org.halvors.nuclearphysics.common.container.machine.ContainerQuantumAssembler;
import org.halvors.nuclearphysics.common.tile.machine.TileQuantumAssembler;
import org.halvors.nuclearphysics.common.utility.ResourceUtility;
import org.halvors.nuclearphysics.common.utility.type.ResourceType;

@SideOnly(Side.CLIENT)
public class GuiQuantumAssembler extends GuiComponentContainer<TileQuantumAssembler> {
    public static final ResourceLocation texture = ResourceUtility.getResource(ResourceType.GUI, "gui_quantum_assembler.png");

    public GuiQuantumAssembler(InventoryPlayer inventoryPlayer, TileQuantumAssembler tile) {
        super(tile, new ContainerQuantumAssembler(inventoryPlayer, tile));

        ySize = 230;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString(tile.getName(), (xSize / 2) - (fontRendererObj.getStringWidth(tile.getName()) / 2), 6, 0x404040);

        String displayText;

        if (tile.timer > 0) {
            displayText = "Process: " + (int) (100 - ((float) tile.timer / (float) TileQuantumAssembler.tickTime) * 100) + "%";
        } else if (tile.canProcess()) {
            displayText = "Ready";
        } else {
            displayText = "Idle";
        }

        fontRendererObj.drawString(displayText, 9, ySize - 106, 0x404040);

        //renderUniversalDisplay(100, ySize - 94, tile.getVoltageInput(null), mouseX, mouseY, UnitDisplay.Unit.VOLTAGE);
        //renderUniversalDisplay(8, ySize - 95, TileQuantumAssembler.tickTime, mouseX, mouseY, UnitDisplay.Unit.WATT);

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        RenderUtility.bindTexture(texture);

        GlStateManager.color(1, 1, 1, 1);

        int containerWidth = (width - xSize) / 2;
        int containerHeight = (height - ySize) / 2;

        drawTexturedModalRect(containerWidth, containerHeight, 0, 0, xSize, ySize);

        super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
    }
}