package org.halvors.quantum.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.quantum.common.ConfigurationManager;

import java.util.Random;

public class BlockUraniumOre extends BlockRadioactive {
    public BlockUraniumOre() {
        super("uranium_ore", Material.ROCK);

        setSoundType(SoundType.STONE);
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 2);

        isRandomlyRadioactive = ConfigurationManager.General.allowRadioactiveOres;
        canWalkPoison = ConfigurationManager.General.allowRadioactiveOres;
        canSpread = false;
        radius = 1.0F;
        amplifier = 0;
        spawnParticle = false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        if (ConfigurationManager.General.allowRadioactiveOres) {
            super.randomDisplayTick(state, world, pos, random);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }
}
