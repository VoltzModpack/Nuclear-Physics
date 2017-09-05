package org.halvors.nuclearphysics.client.gui.modular.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.client.gui.modular.GuiComponentContainer;
import org.halvors.nuclearphysics.client.gui.modular.component.GuiFluidGauge;
import org.halvors.nuclearphysics.client.gui.modular.component.GuiProgress;
import org.halvors.nuclearphysics.client.gui.modular.component.GuiSlot;
import org.halvors.nuclearphysics.client.gui.modular.component.GuiSlot.SlotType;
import org.halvors.nuclearphysics.common.container.machine.ContainerChemicalExtractor;
import org.halvors.nuclearphysics.common.tile.machine.TileChemicalExtractor;
import org.halvors.nuclearphysics.common.utility.LanguageUtility;

@SideOnly(Side.CLIENT)
public class GuiChemicalExtractor extends GuiComponentContainer<TileChemicalExtractor> {
    public GuiChemicalExtractor(InventoryPlayer inventory, TileChemicalExtractor tile) {
        super(tile, new ContainerChemicalExtractor(inventory, tile));

        components.add(new GuiSlot(SlotType.BATTERY, this, 79, 49));
        components.add(new GuiSlot(this, 52, 24));
        components.add(new GuiSlot(this, 106, 24));
        components.add(new GuiProgress(this, 75, 24, tile.timer / TileChemicalExtractor.tickTime));
        // TODO: Set fixed fluid here.
        components.add(new GuiFluidGauge(tile::getInputTank, this, 8, 18));
        components.add(new GuiSlot(SlotType.LIQUID, this, 24, 18));
        components.add(new GuiSlot(SlotType.LIQUID, this, 24, 49));
        components.add(new GuiFluidGauge(tile::getOutputTank, this, 154, 18));
        components.add(new GuiSlot(SlotType.LIQUID, this, 134, 18));
        components.add(new GuiSlot(SlotType.LIQUID, this, 134, 49));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString(tile.getName(), (xSize / 2) - (fontRendererObj.getStringWidth(tile.getName()) / 2), 6, 0x404040);

        //renderUniversalDisplay(8, 112, TileChemicalExtractor.energy * 20, mouseX, mouseY, UnitDisplay.Unit.WATT);
        //renderUniversalDisplay(100, 112, tile.getVoltageInput(null), mouseX, mouseY, UnitDisplay.Unit.VOLTAGE);

        // TODO: Transelate this.
        fontRendererObj.drawString("The extractor can extract", 8, 75, 0x404040);
        fontRendererObj.drawString("uranium, deuterium and tritium.", 8, 85, 0x404040);
        fontRendererObj.drawString("Place them in the input slot.", 8, 95, 0x404040);

        fontRendererObj.drawString(LanguageUtility.transelate("container.inventory"), 8, (ySize - 96) + 2, 0x404040);

        /*
        if (isPointInRegion(134, 49, 18, 18, mouseX, mouseY)) {
            if (tile.getInventory().getStackInSlot(4) == null) {
                // drawTooltip(x - guiLeft, y - guiTop + 10, "Place empty cells.");
            }
        }

        if (isPointInRegion(52, 24, 18, 18, mouseX, mouseY)) {
            if (tile.getOutputTank().getFluidAmount() > 0 && tile.getInventory().getStackInSlot(3) == null) {
                drawTooltip(mouseX - guiLeft, mouseY - guiTop + 10, "Input slot");
            }
        }
        */

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}